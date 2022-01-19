package com.xiaozu.server.exception;

import java.text.MessageFormat;

/**
 * @author dongpo.li
 * @date 2022/1/19
 */
public class BusinessException extends RuntimeException {

    private final BasicResponseStatus status;
    private final Object[] args;

    public BusinessException(BasicResponseStatus status) {
        super("业务异常, code=" + status.getCode() + ", message=" + status.getMessage());
        this.status = status;
        this.args = new Object[]{};
    }

    public BusinessException(BasicResponseStatus status, Object... args) {
        super("业务异常, code=" + status.getCode() + ", message=" + MessageFormat.format(status.getMessage(), args));
        this.status = status;
        this.args = new Object[]{};
    }

    public BasicResponseStatus getStatus() {
        return status;
    }
}
