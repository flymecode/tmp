package com.xupt.tmp.controller;

import com.xupt.tmp.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseController {
    @Autowired
    public TokenUtils tokenUtils;

    public boolean invalidId(Long value) {
        if (null == value || value.longValue() < 1L) {
            return true;
        }
        return false;
    }
}
