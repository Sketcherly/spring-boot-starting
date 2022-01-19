package com.xiaozu.server.utils;

import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 属性赋值工具类
 * 使用这个工具有几点要求
 * 1. setter方法的参数类型一定要和属性类型一致,所以如果lombok开启了链式调用或者使用了Builder注解的话是不能使用的
 * ..-- lombok侵入性太强,不建议使用
 * 2. getter方法的返回值一定要是void
 * 3. 转换的目标有一个无参的构造方法
 *
 * @author dongpo.li
 * @date 2021/8/14
 */
public class BeanCopyHelper<S> {

    private static final Splitter splitter = Splitter.on("->").trimResults().omitEmptyStrings();

    private final S src; // 源数据bean
    private final String[] fields;

    /**
     * 构造方法  new BeanCopyHelper(src, "field1", "field2 -> field2_2")
     *
     * @param src    源bean实例
     * @param fields 需要复制的字段列表,变长参数,不包含"->"说明源和目标同名,否则不同名
     */
    public BeanCopyHelper(S src, String... fields) {
        this.src = src;
        this.fields = fields;
    }

    public <D> D copy(Class<D> clazz) {
        try {
            Constructor<D> constructor = clazz.getConstructor();
            D dst = constructor.newInstance();

            // 必须指定需要复制的字段是尽量减少返回的报文大小,同时防止无意返回了包含敏感数据的字段
            // 所以尽量使用这个工具返回vo,不要怕麻烦
            if (fields == null || fields.length == 0) {
                return dst;
            }

            for (String field : fields) {
                if (StringUtils.isBlank(field)) {
                    continue;
                }

                String srcFiledName = "";
                String dstFiledName = "";

                List<String> fieldList = splitter.splitToList(field);
                if (fieldList.size() == 1) {
                    srcFiledName = field;
                    dstFiledName = field;
                } else {
                    srcFiledName = fieldList.get(0);
                    dstFiledName = fieldList.get(1);
                }


                PropertyDescriptor srcPropDesc = new PropertyDescriptor(srcFiledName, src.getClass());
                PropertyDescriptor dstPropDesc = new PropertyDescriptor(dstFiledName, dst.getClass());

                Method srcReadMethod = srcPropDesc.getReadMethod();
                Method dstWriteMethod = dstPropDesc.getWriteMethod();

                Object srcValue = srcReadMethod.invoke(src);
                dstWriteMethod.invoke(dst, srcValue);
            }

            return dst;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
