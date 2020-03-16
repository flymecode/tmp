package com.xupt.tmp.exception;


import com.xupt.tmp.enums.HttpCodeEnum;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ForbiddenException(String message) {
        super(message);
    }


    public ForbiddenException() {
        super(HttpCodeEnum.FORBIDDEN.getMessage());
    }
}
