package com.xupt.tmp.service;

import com.xupt.tmp.dto.signDto.SignCreate;
import com.xupt.tmp.model.SignRecord;

import java.util.List;

public interface SignService {
    void createSign(SignCreate signCreate);

    List<SignRecord> getSignRecord(String signRecord);

    void sign(String username);
}
