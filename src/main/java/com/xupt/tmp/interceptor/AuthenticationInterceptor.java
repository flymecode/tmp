package com.xupt.tmp.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.xupt.tmp.annotion.AuthIgnore;
import com.xupt.tmp.annotion.AuthShare;
import com.xupt.tmp.common.Consts;
import com.xupt.tmp.model.User;
import com.xupt.tmp.enums.HttpCodeEnum;
import com.xupt.tmp.dto.ResultMap;
import com.xupt.tmp.service.UserService;
import com.xupt.tmp.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod = null;
        try {
            handlerMethod = (HandlerMethod) handler;
        } catch (Exception e) {
            response.setStatus(HttpCodeEnum.NOT_FOUND.getCode());
            return false;
        }

        Method method = handlerMethod.getMethod();

        AuthIgnore ignoreAuthMethod = method.getAnnotation(AuthIgnore.class);
        //注解不需要验证token
        if (handler instanceof HandlerMethod && null != ignoreAuthMethod) {
            return true;
        }

        String token = request.getHeader(Consts.TOKEN_HEADER_STRING);

        AuthShare authShareMethod = method.getAnnotation(AuthShare.class);
        if (null != authShareMethod) {
            if (!StringUtils.isEmpty(token) && token.startsWith(Consts.TOKEN_PREFIX)) {
                String username = tokenUtils.getUsername(token);
                User user = userService.getByUsername(username);
                request.setAttribute(Consts.CURRENT_USER, user);
            }
            return true;
        }

        if (StringUtils.isEmpty(token) || !token.startsWith(Consts.TOKEN_PREFIX)) {
            if (!request.getServletPath().endsWith("/download/page")) {
                log.debug("{} : Unknown token", request.getServletPath());
            }
            response.setStatus(HttpCodeEnum.FORBIDDEN.getCode());
            response.getWriter().print("The resource requires authentication, which was not supplied with the request");
            return false;
        }
        String username = tokenUtils.getUsername(token);
        User user = userService.getByUsername(username);
        if (null == user) {
            if (!request.getServletPath().endsWith("/download/page")) {
                log.debug("{} : token user not found", request.getServletPath());
            }
            response.setStatus(HttpCodeEnum.FORBIDDEN.getCode());
            response.getWriter().print("ERROR Permission denied");
            return false;

        }
        if (!tokenUtils.validateToken(token, user)) {
            if (!request.getServletPath().endsWith("/download/page")) {
                log.debug("{} : token validation fails", request.getServletPath());
            }
            response.setStatus(HttpCodeEnum.FORBIDDEN.getCode());
            response.getWriter().print("Invalid token ");
            return false;
        }

        if (!request.getServletPath().contains("/user/active") && !user.getActive()) {
            if (request.getServletPath().contains("/user/sendmail")) {
                request.setAttribute(Consts.CURRENT_USER, user);
                return true;
            }
            log.info("current user is not activated, username: {}", user.getUsername());
            response.setStatus(HttpCodeEnum.FAIL.getCode());
            ResultMap resultMap = new ResultMap(tokenUtils);
            response.getWriter().print(JSONObject.toJSONString(resultMap.failAndRefreshToken(request).message("Account not active yet. Please check your email to activate your account")));
            return false;
        }
        request.setAttribute(Consts.CURRENT_USER, user);
        return true;
    }

}
