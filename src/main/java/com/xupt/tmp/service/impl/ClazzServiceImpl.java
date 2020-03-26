package com.xupt.tmp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xupt.tmp.dto.clazzDto.CreateClazz;
import com.xupt.tmp.dto.userDto.UserUpload;
import com.xupt.tmp.mapper.ClazzMapper;
import com.xupt.tmp.mapper.SelectCourseTableMapper;
import com.xupt.tmp.model.Clazz;
import com.xupt.tmp.model.SelectCourseTable;
import com.xupt.tmp.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Autowired
    private SelectCourseTableMapper selectCourseTableMapper;

    @Override
    public List<Clazz> getClazzs(long courseId) {
        List<Clazz> clazzs = clazzMapper.selectClazzs(courseId);
        if (clazzs == null) {
            return new ArrayList();
        }
        return clazzs;
    }

    @Override
    @Transient
    public void createClazz(CreateClazz createClazz) {
        Clazz clazz = new Clazz();
        Long courseId = createClazz.getCourseId();
        clazz.setCourseId(createClazz.getCourseId());
        clazz.setName(createClazz.getName());
        List<UserUpload> students = createClazz.getStudents();
        clazz.setStudents(JSONObject.toJSONString(students));
        if (clazzMapper.insert(clazz) > 0) {
            for (UserUpload student : students) {
                String courseTable = selectCourseTableMapper.selectCourseTable(student.getUsername());
                if (courseTable == null) {
                    SelectCourseTable selectCourseTable = new SelectCourseTable();
                    selectCourseTable.setUsername(student.getUsername());
                    selectCourseTable.setCourseIds("[" + courseId + "]");
                    selectCourseTableMapper.insert(selectCourseTable);
                } else {
                    if (!courseTable.contains(String.valueOf(courseId))) {
                        List<Long> newCourseTable = JSONObject.parseArray(courseTable, Long.class);
                        newCourseTable.add(courseId);
                        selectCourseTableMapper.update(student.getUsername(), newCourseTable.toString());
                    }
                }
            }
        }

    }

    @Override
    public List<UserUpload> getClazzStudnets(long clazzId) {
        String students = clazzMapper.selectStudents(clazzId);
        List<UserUpload> userUploads = JSONObject.parseArray(students, UserUpload.class);
        if (userUploads == null ) {
            return new ArrayList<>();
        }
        return userUploads;
    }
}
