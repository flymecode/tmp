package com.xupt.tmp.service;

import com.xupt.tmp.dto.clazzDto.CreateClazz;
import com.xupt.tmp.dto.userDto.UserUpload;
import com.xupt.tmp.model.Clazz;

import java.util.List;
public interface ClazzService {

    List<Clazz> getClazzs(long courseId);

    void createClazz(CreateClazz createClazz);

    List<UserUpload> getClazzStudnets(long clazzId);
}
