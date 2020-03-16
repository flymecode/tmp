package com.xupt.tmp.service.impl;

import com.xupt.tmp.model.User;
import com.xupt.tmp.model.Work;
import com.xupt.tmp.mapper.WorkMapper;
import com.xupt.tmp.dto.workDto.WorkCreate;
import com.xupt.tmp.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    private WorkMapper workMapper;
    /**
     * 创建测试任务
     */
    @Override
    @Transactional
    public Work createWork(WorkCreate workCreate, User user) {
        return null;
    }
}
