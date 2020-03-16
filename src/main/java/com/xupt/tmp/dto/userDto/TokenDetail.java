package com.xupt.tmp.dto.userDto;

import lombok.Data;

@Data
public class TokenDetail {
    /**
     * 学生/老师一卡通账号
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
