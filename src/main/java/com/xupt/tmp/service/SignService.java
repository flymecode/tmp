package com.xupt.tmp.service;

import com.xupt.tmp.dto.signDto.SignCreate;
import com.xupt.tmp.dto.signDto.SignTaskResult;
import com.xupt.tmp.model.SignRecord;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface SignService {
    void createSign(SignCreate signCreate, HttpServletRequest request);

    List<SignRecord> getSignRecord(Long id);

    void sign(String username);

    List<SignTaskResult> getSignTasks(String username);
}
