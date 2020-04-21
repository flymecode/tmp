package com.xupt.tmp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xupt.tmp.model.User;
import com.xupt.tmp.exception.ServerException;
import com.xupt.tmp.mapper.UserMapper;
import com.xupt.tmp.dto.userDto.UserLogin;
import com.xupt.tmp.dto.userDto.UserRegist;
import com.xupt.tmp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User userLogin(UserLogin userLogin) throws ServerException {
        String username = userLogin.getUsername();
        String password = userLogin.getPassword();
        User user = getByUsername(username);
        if (user != null) {
            // 校验密码
            boolean checkpw = BCrypt.checkpw(password, user.getPassword());
            if (checkpw) {
                return user;
            }
        }
        if (user == null) {
            throw new ServerException("username or password is wrong");
        }
        return user;
    }

    public User getByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public User register(UserRegist userRegist) {
        String username = userRegist.getUsername();
        //用户名是否已经注册
        if (isExist(username)) {
            log.info("the username {} has been registered", username);
            throw new ServerException("the username:" + username + " has been registered");
        }
        User user = new User();
        //密码加密
        userRegist.setPassword(BCrypt.hashpw(userRegist.getPassword(), BCrypt.gensalt()));
        BeanUtils.copyProperties(userRegist, user);
        user.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        ArrayList<String> roles = new ArrayList<>();
        if (userRegist.getType() == 1) {
            roles.add("student");
        } else {
            roles.add("teacher");
        }
        user.setRoles(JSONObject.toJSONString(roles));
        //添加用户
        if (userMapper.insert(user) <= 0) {
            log.info("register fail: {}", userRegist.toString());
            throw new ServerException("register fail: unspecified error");
        }
        //添加成功，发送激活邮件
        //sendMail(user.getEmail(), user);
        return user;
    }

    /**
     * 用户是否存在
     */
    @Override
    public boolean isExist(String name) {
        String userId = userMapper.getIdByName(name);
        return !StringUtils.isEmpty(userId);
    }
}
