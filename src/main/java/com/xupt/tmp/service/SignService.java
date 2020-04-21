package com.xupt.tmp.service;

import com.github.pagehelper.PageInfo;
import com.xupt.tmp.dto.ResultMap;
import com.xupt.tmp.dto.signDto.SignCreate;
import com.xupt.tmp.dto.signDto.SignQuery;
import com.xupt.tmp.dto.signDto.SignTaskResult;
import com.xupt.tmp.model.SignRecord;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface SignService {
    void createSign(SignCreate signCreate, HttpServletRequest request);

    List<SignRecord> getSignRecord(Long id);

    ResultMap sign(long signId, String username);

    PageInfo<SignTaskResult> getSignTasks(String username, SignQuery signQuery);

    List<SignTaskResult> getSignRecords(String username);
}
