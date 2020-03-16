
package com.xupt.tmp.exception;


import com.xupt.tmp.enums.HttpCodeEnum;

public class UnAuthorizedException extends RuntimeException {

    public UnAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnAuthorizedException(String message) {
        super(message);
    }


    public UnAuthorizedException() {
        super(HttpCodeEnum.UNAUTHORIZED.getMessage());
    }
}
