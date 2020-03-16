package com.xupt.tmp.controller;

import com.xupt.tmp.common.Consts;
import com.xupt.tmp.model.User;
import com.xupt.tmp.dto.ResultMap;
import com.xupt.tmp.dto.userDto.UserLogin;
import com.xupt.tmp.service.UserService;
import com.xupt.tmp.utils.TokenUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping(value = Consts.BASE_API_PATH + "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LoginController {


    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtils tokenUtils;

    /**
     * 登录
     */
    @ApiOperation(value = "用户登陆并返回token")
    @PostMapping(value = "/login")
    public ResponseEntity login(@Valid @RequestBody UserLogin userLogin, @ApiIgnore BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ResultMap resultMap = new ResultMap().fail()
                    .message(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.status(resultMap.getCode()).body(resultMap);
        }
        User user = userService.userLogin(userLogin);
        return ResponseEntity.ok(new ResultMap().success(tokenUtils.generateToken(user)));
    }
}
