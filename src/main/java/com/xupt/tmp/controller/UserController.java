package com.xupt.tmp.controller;

import com.xupt.tmp.annotion.CurrentUser;
import com.xupt.tmp.common.Consts;
import com.xupt.tmp.dto.ResultMap;
import com.xupt.tmp.dto.userDto.UserLoginResult;
import com.xupt.tmp.dto.userDto.UserRegist;
import com.xupt.tmp.model.User;
import com.xupt.tmp.service.SelectCourseTableService;
import com.xupt.tmp.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = Consts.BASE_API_PATH + "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private SelectCourseTableService selectCourseTableService;

    /**
     * 用户注册
     */
    @ApiOperation("用户注册")
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity register(@Valid @RequestBody UserRegist userRegist, @ApiIgnore BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ResultMap resultMap = new ResultMap().fail().message(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.status(resultMap.getCode()).body(resultMap);
        }
        User user = userService.register(userRegist);
        return ResponseEntity.ok(new ResultMap().success().payload(tokenUtils.generateToken(user)));
    }

    @GetMapping(value = "/info")
    public ResponseEntity getUserInfo(@ApiIgnore @CurrentUser User user) {

        UserLoginResult userLoginResult = new UserLoginResult(user);
        List<String> roles = userLoginResult.getRoles();
        if (roles.contains("student")) {
            List<Long> courseTable = selectCourseTableService.getCourseTable(user.getUsername());
            userLoginResult.setCourseTable(courseTable);
        }
        return ResponseEntity.ok(new ResultMap().success().payload(userLoginResult));
    }

    @PostMapping(value = "/logout")
    public ResponseEntity logout(HttpServletRequest request) {
        request.setAttribute(Consts.CURRENT_USER, Consts.EMPTY);
        return ResponseEntity.ok(new ResultMap().success());
    }

}
