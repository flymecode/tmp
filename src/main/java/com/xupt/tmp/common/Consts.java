package com.xupt.tmp.common;

/**
 * 常量
 */
public interface Consts {
    String TOKEN_USER_NAME = "token_user_name";
    String TOKEN_USER_PASSWORD = "token_user_password";
    String TOKEN_CREATE_TIME = "token_create_time";
    String EMPTY = "";
    String TOKEN_PREFIX = "Bearer";
    String BASE_API_PATH = "/api/v1/";
    String TOKEN_HEADER_STRING = "authorization";
    String REG_USER_PASSWORD = ".{6,20}";
    String REG_EMAIL_FORMAT = "^[a-z_0-9.-]{1,64}@([a-z0-9-]{1,200}.){1,5}[a-z]{1,6}$";
    String CURRENT_USER = "current_user";
}
