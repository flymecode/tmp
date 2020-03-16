package com.xupt.tmp.service;

import com.xupt.tmp.model.User;
import com.xupt.tmp.model.Work;
import com.xupt.tmp.dto.workDto.WorkCreate;

public interface WorkService {
    Work createWork(WorkCreate workCreate, User user);
}
