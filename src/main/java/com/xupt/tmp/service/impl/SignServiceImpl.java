package com.xupt.tmp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xupt.tmp.dto.signDto.SignCreate;
import com.xupt.tmp.dto.userDto.UserUpload;
import com.xupt.tmp.mapper.ClazzMapper;
import com.xupt.tmp.mapper.SignMapper;
import com.xupt.tmp.model.SignRecord;
import com.xupt.tmp.model.SignTask;
import com.xupt.tmp.service.SignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class SignServiceImpl implements SignService {
    @Autowired
    private SignMapper signMapper;

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    @Transient
    public void createSign(SignCreate signCreate) {
        signCreate.setCreateTime(new Date());
        signCreate.setStartTime(new Date());
        signCreate.setEndTime(new Date());
        SignTask signTask = new SignTask();
        BeanUtils.copyProperties(signCreate, signTask);
        signMapper.insertSignTask(signTask);
        long id = signTask.getId();
        String students = clazzMapper.selectStudents(signCreate.getClazzId());
        List<UserUpload> userUploads = JSONObject.parseArray(students, UserUpload.class);
        List<SignRecord> list = new ArrayList<>();
        for (UserUpload userUpload : userUploads) {
            SignRecord signRecord = new SignRecord(userUpload);
            signRecord.setSignId(id);
            list.add(signRecord);
        }
        signMapper.insertSignRecord(list);
    }

    @Override
    public List<SignRecord> getSignRecord(String username) {
        List<SignRecord> signRecords = signMapper.selectSignRecords(username);
        return signRecords;
    }

    @Override
    public void sign(String username) {
        signMapper.updateSignRecord(username,new Date());
    }

}
