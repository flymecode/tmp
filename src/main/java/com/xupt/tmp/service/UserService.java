package com.xupt.tmp.service;

import com.xupt.tmp.model.User;
import com.xupt.tmp.exception.ServerException;
import com.xupt.tmp.dto.userDto.UserLogin;
import com.xupt.tmp.dto.userDto.UserRegist;

public interface UserService {
    User userLogin(UserLogin userLogin) throws ServerException;

    User getByUsername(String username);

    User register(UserRegist userRegist);

    boolean isExist(String name);
}
