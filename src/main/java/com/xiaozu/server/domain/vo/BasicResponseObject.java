package com.xiaozu.server.domain.vo;

import com.xiaozu.server.exception.BasicResponseStatus;

/**
 * @author dongpo.li
 * @date 2022/1/14
 */
public class BasicResponseObject<T> {

    private int code;
    private String message;
    private T data;

    // 不推荐,业务中推荐直接抛出异常的形式,参见BusinessException
    public BasicResponseObject() {
    }

    // 不推荐,业务中推荐直接抛出异常的形式,参见BusinessException
    public static <T> BasicResponseObject<T> ok(T data) {
        BasicResponseObject<T> obj = new BasicResponseObject<>();
        obj.setCode(BasicResponseStatus.OK.getCode());
        obj.setMessage(BasicResponseStatus.OK.getMessage());
        obj.setData(data);

        return obj;
    }

    // 不推荐,业务中推荐直接抛出异常的形式,参见BusinessException
    public static <T> BasicResponseObject<T> fail(int code, String message) {
        BasicResponseObject<T> obj = new BasicResponseObject<>();
        obj.setCode(code);
        obj.setMessage(message);
        obj.setData(null);

        return obj;
    }

    // 不推荐,业务中推荐直接抛出异常的形式,参见BusinessException
    public static <T> BasicResponseObject<T> fail(BasicResponseStatus status) {
        BasicResponseObject<T> obj = new BasicResponseObject<>();
        obj.setCode(status.getCode());
        obj.setMessage(status.getMessage());
        obj.setData(null);

        return obj;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
