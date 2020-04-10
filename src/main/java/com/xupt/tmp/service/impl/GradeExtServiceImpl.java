package com.xupt.tmp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xupt.tmp.dto.gradeExtDto.GradeExtQuery;
import com.xupt.tmp.dto.gradeExtDto.GradeExtResult;
import com.xupt.tmp.dto.userDto.UserUpload;
import com.xupt.tmp.mapper.ClazzMapper;
import com.xupt.tmp.mapper.GradeExtMapper;
import com.xupt.tmp.model.GradeExt;
import com.xupt.tmp.service.GradeExtService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeExtServiceImpl implements GradeExtService {
    @Autowired
    private GradeExtMapper gradeExtMapper;

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public List<GradeExtResult> getGradeExts(GradeExtQuery query, HttpServletRequest request) {
        String students = clazzMapper.selectStudents(query.getClazzId());
        long clazzId = query.getClazzId();
        long courseId = query.getCourseId();
        List<UserUpload> users = JSONObject.parseArray(students, UserUpload.class);
        List<GradeExtResult> collect = users.stream().map(userUpload -> {
            GradeExtResult result = new GradeExtResult();
            BeanUtils.copyProperties(userUpload, result);
            int grade = gradeExtMapper.selectGradeSum(userUpload.getUsername(), courseId, clazzId);
            result.setResult(grade);
            return result;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public void createGradeExt(GradeExt gradeExt) {
        gradeExtMapper.insert(gradeExt);
    }

    @Override
    public void updateGradeExt(GradeExt gradeExt) {
        gradeExtMapper.updateGradeExt(gradeExt);
    }

    @Override
    public List<GradeExt> getGradeExtsInfo(GradeExtQuery query) {
        return gradeExtMapper.selectGradeExtsInfo(query);
    }

    @Override
    public void deleteGradeExt(long id) {
        gradeExtMapper.deleteGradeExtById(id);
    }

}
