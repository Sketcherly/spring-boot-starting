package com.xiaozu.server.exception;

/**
 * @author dongpo.li
 * @date 2022/1/19
 */
public enum BasicResponseStatus {

    OK(200, "ok"),
    // 除400以外都是开发阶段才会有的错误
    HTTP_UNAUTHORIZED(401, "Unauthorized"),
    HTTP_FORBIDDEN(403, "Forbidden"),
    HTTP_NOT_FOUND(404, "Not Found"),
    HTTP_METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    HTTP_UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),
    HTTP_INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    // 自定义的异常code请从10000开始,10000以前的数字保留备用

    // 大多数异常可以直接使用这个错误码,前端直接toast,其他错误码由前端自行处理
    // 10000和500的区别是10000不会在日志里打印异常栈,500会打印异常栈
    // 10000说明是正常的业务失败,500说明业务出现了不可预知的异常
    BASIC_SERVER_EXCEPTION(10000, "{0}"),
    ;

    private final int code;
    private final String message;

    BasicResponseStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public BusinessException createException(Object... args) {
        if (args.length == 0) {
            return new BusinessException(this);
        }

        return new BusinessException(this, args);
    }
}
