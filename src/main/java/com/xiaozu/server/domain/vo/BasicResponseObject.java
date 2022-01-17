package com.xiaozu.server.domain.vo;

import javax.servlet.http.HttpServletResponse;

/**
 * @author dongpo.li
 * @date 2022/1/14
 */
public class BasicResponseObject<T> {

    private int code;
    private String message;
    private T data;

    public BasicResponseObject() {
    }

    public static <T> BasicResponseObject<T> ok(T data) {
        BasicResponseObject<T> obj = new BasicResponseObject<>();
        obj.setCode(HttpServletResponse.SC_OK);
        obj.setMessage("ok");
        obj.setData(data);

        return obj;
    }

    public static <T> BasicResponseObject<T> fail(int code, String message) {
        BasicResponseObject<T> obj = new BasicResponseObject<>();
        obj.setCode(code);
        obj.setMessage(message);
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
