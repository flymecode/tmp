package com.xupt.tmp.dto.userDto;

import com.xupt.tmp.common.Consts;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserLogin {
    @NotBlank(message = "username cannot be EMPTY")
    private String username;

    @NotBlank(message = "password cannot be EMPTY")
    @Pattern(regexp = Consts.REG_USER_PASSWORD, message = "密码长度为6-20位")
    private String password;
}
