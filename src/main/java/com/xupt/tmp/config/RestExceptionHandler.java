package com.xupt.tmp.config;

import com.xupt.tmp.enums.HttpCodeEnum;
import com.xupt.tmp.exception.ForbiddenException;
import com.xupt.tmp.exception.NotFoundException;
import com.xupt.tmp.exception.ServerException;
import com.xupt.tmp.exception.UnAuthorizedException;
import com.xupt.tmp.dto.ResultMap;
import com.xupt.tmp.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(annotations = RestController.class)
@Slf4j
public class RestExceptionHandler {
    @Autowired
    private TokenUtils tokenUtils;

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResultMap commonExceptionHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return new ResultMap(tokenUtils).failAndRefreshToken(request).message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    @ExceptionHandler(value = ServerException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResultMap serverExceptionHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return new ResultMap(tokenUtils).failAndRefreshToken(request).message(e.getMessage());
    }

    @ExceptionHandler(value = ForbiddenException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    private ResultMap forbiddenExceptionHandler(HttpServletRequest request, Exception e) {
        log.error(e.getMessage());
        return new ResultMap(tokenUtils).failAndRefreshToken(request, HttpCodeEnum.FORBIDDEN).message(e.getMessage());
    }

    @ExceptionHandler(value = UnAuthorizedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    private ResultMap unAuthorizedExceptionHandler(HttpServletRequest request, Exception e) {
        log.error(e.getMessage());
        return new ResultMap(tokenUtils).failAndRefreshToken(request, HttpCodeEnum.UNAUTHORIZED).message(e.getMessage());
    }

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResultMap notFoundExceptionHandler(HttpServletRequest request, Exception e) {
        log.error(e.getMessage());
        return new ResultMap(tokenUtils).failAndRefreshToken(request, HttpCodeEnum.NOT_FOUND).message(e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResultMap methodArgumentNotValidExceptionHandler(HttpServletRequest request, Exception e) {
        log.error(e.getMessage());
        return new ResultMap(tokenUtils).failAndRefreshToken(request, HttpCodeEnum.FAIL).message(e.getMessage());
    }
}

