package com.xupt.tmp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xupt.tmp.dto.clazzDto.CreateClazz;
import com.xupt.tmp.dto.userDto.UserRegist;
import com.xupt.tmp.dto.userDto.UserUpload;
import com.xupt.tmp.mapper.ClazzMapper;
import com.xupt.tmp.mapper.SelectCourseTableMapper;
import com.xupt.tmp.mapper.UCCMapper;
import com.xupt.tmp.model.Clazz;
import com.xupt.tmp.model.SelectCourseTable;
import com.xupt.tmp.service.ClazzService;
import com.xupt.tmp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Autowired
    private SelectCourseTableMapper selectCourseTableMapper;

    @Autowired
    private UCCMapper uccMapper;

    @Autowired
    private UserService userService;

    @Override
    public List<Clazz> getClazzs(long courseId) {
        List<Clazz> clazzs = clazzMapper.selectClazzs(courseId);
        if (clazzs == null) {
            return new ArrayList();
        }
        return clazzs;
    }

    @Override
    @Transactional
    public void createClazz(CreateClazz createClazz) {
        Clazz clazz = new Clazz();
        long courseId = createClazz.getCourseId();
        clazz.setCourseId(courseId);
        clazz.setName(createClazz.getName());
        List<UserUpload> students = createClazz.getStudents();
        clazz.setNum(students.size());
        // TODO 不应该转为json
        clazz.setStudents(JSONObject.toJSONString(students));
        if (clazzMapper.insert(clazz) > 0) {
            for (UserUpload student : students) {
                UserRegist userRegist = new UserRegist();
                userRegist.setName(student.getName());
                userRegist.setPassword(student.getUsername());
                userRegist.setUsername(student.getUsername());
                userRegist.setType(1);
                boolean exist = userService.isExist(student.getUsername());
                if (!exist) {
                    userService.register(userRegist);
                }
                uccMapper.insert(courseId, clazz.getId(), student.getUsername(), student.getName());
            }
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
        if (userUploads == null) {
            return new ArrayList<>();
        }
        return userUploads;
    }
}
