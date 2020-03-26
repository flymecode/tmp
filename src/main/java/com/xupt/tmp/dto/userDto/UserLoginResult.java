package com.xupt.tmp.dto.userDto;

import com.alibaba.fastjson.JSONObject;
import com.xupt.tmp.model.User;
import lombok.Data;

import java.util.List;

@Data
public class UserLoginResult extends UserBaseInfo {
    private String name;
    private List<String> roles;
    private List<Long> courseTable;
    public UserLoginResult(User user) {
        this.name = user.getName();
        this.username = user.getUsername();
        this.avatar = user.getAvatar();
        this.email = user.getEmail();
        this.roles = JSONObject.parseArray(user.getRoles(), String.class);
    }

    public UserLoginResult() {
    }
}
