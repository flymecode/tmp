package com.xupt.tmp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xupt.tmp.common.Consts;
import com.xupt.tmp.dto.ResultMap;
import com.xupt.tmp.dto.signDto.SignCreate;
import com.xupt.tmp.dto.signDto.SignTaskResult;
import com.xupt.tmp.dto.userDto.UserUpload;
import com.xupt.tmp.mapper.ClazzMapper;
import com.xupt.tmp.mapper.SignMapper;
import com.xupt.tmp.mapper.UCCMapper;
import com.xupt.tmp.model.SignRecord;
import com.xupt.tmp.model.SignTask;
import com.xupt.tmp.model.UCCRelation;
import com.xupt.tmp.model.User;
import com.xupt.tmp.service.SignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SignServiceImpl implements SignService {
    @Autowired
    private SignMapper signMapper;

    @Autowired
    private ClazzMapper clazzMapper;

    @Autowired
    private UCCMapper uccMapper;

    @Override
    @Transactional
    public void createSign(SignCreate signCreate, HttpServletRequest request) {
        Long[] date = signCreate.getDate();
        Long[] value = signCreate.getValue();
        SignTask signTask = new SignTask();
        signTask.setStartTime(new Date(date[0]));
        signTask.setEndTime(new Date(date[1]));
        signTask.setCreateTime(new Date());
        signTask.setCourseId(value[0]);
        signTask.setClazzId(value[1]);
        User user = (User) request.getAttribute(Consts.CURRENT_USER);
        signTask.setCreateId(user.getUsername());
        signTask.setName(signCreate.getName());
        signMapper.insertSignTask(signTask);

        long id = signTask.getId();
        String students = clazzMapper.selectStudents(signTask.getClazzId());
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
    public List<SignRecord> getSignRecord(Long id) {
        List<SignRecord> signRecords = signMapper.selectSignRecords(id);
        return signRecords;
    }

    @Override
    public ResultMap sign(long signId, String username) {
        ResultMap resultMap = new ResultMap();
        SignTask task = signMapper.selectSignTaskById(signId);
        long cur = System.currentTimeMillis();
        Date endTime = task.getEndTime();
        long time = endTime.getTime();
        if (time < cur) {
            resultMap.fail().message("签到时间已经截至");
            return resultMap;
        }
        signMapper.updateSignRecord(username, signId, new Date());
        return resultMap.success().message("签到成功");
    }

    @Override
    public List<SignTaskResult> getSignTasks(String username) {
        List<SignTask> signTasks = signMapper.selectSignTasks(username);
        return signTasks.stream().map(SignTaskResult::new).collect(Collectors.toList());
    }

    @Override
    public List<SignTaskResult> getSignRecords(String username) {

        List<SignTask> result = new ArrayList<>();
        List<UCCRelation> uccRelations = uccMapper.selectRelation(username);
        if (uccRelations == null) {
            return new ArrayList<>();
        }
        for (UCCRelation uccRelation : uccRelations) {
            List<SignTask> signRecords = signMapper.selectSignTask(uccRelation.getCourseId(), uccRelation.getClazzId(), username);
            if (signRecords != null) {
                result.addAll(signRecords);
            }
        }
        return result.stream().map(SignTaskResult::new).collect(Collectors.toList());
    }

}
