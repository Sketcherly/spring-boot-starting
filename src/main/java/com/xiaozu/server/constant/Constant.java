package com.xiaozu.server.constant;

import com.google.common.base.Splitter;

/**
 * @author dongpo.li
 * @date 2022/1/14
 */
public class Constant {

    public static final String SERVER_ADMIN_NAMESPACE = "/admin";

    public static final Splitter COMMA_SPLITTER = Splitter.on(",").trimResults().omitEmptyStrings();

}
