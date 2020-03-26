package com.xupt.tmp.service.impl;
import com.xupt.tmp.dto.gradeDto.GradeCreate;
import com.xupt.tmp.mapper.GradeMapper;
import com.xupt.tmp.model.Grade;
import com.xupt.tmp.service.GradeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public void addGrade(Grade grade) {
        GradeCreate gradeCreate = new GradeCreate();
        BeanUtils.copyProperties(grade,gradeCreate);
        gradeMapper.insert(grade);
    }
}
