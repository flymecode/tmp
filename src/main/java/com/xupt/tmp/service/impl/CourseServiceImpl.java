package com.xupt.tmp.service.impl;

import com.xupt.tmp.dto.ResultMap;
import com.xupt.tmp.dto.courseDto.CreateCourse;
import com.xupt.tmp.dto.signDto.KV;
import com.xupt.tmp.mapper.ClazzMapper;
import com.xupt.tmp.mapper.CourseMapper;
import com.xupt.tmp.model.Clazz;
import com.xupt.tmp.model.Course;
import com.xupt.tmp.model.User;
import com.xupt.tmp.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public List<Course> getCourses(String createId) {
        return courseMapper.selectCoursesByCreateId(createId);
    }

    @Override
    public List<Course> getCourses(List<Long> courseIds) {
        return courseMapper.selectCoursesByIds(courseIds);
    }

    @Override
    public void createCourse(CreateCourse createCourse) {
        Course course = new Course();
        courseMapper.insert(course);
    }

    @Override
    public void addCourse(User user, CreateCourse createCourse) {
        Course course = new Course();
        BeanUtils.copyProperties(createCourse, course);
        course.setCreateId(user.getUsername());
        course.setCreateName(user.getName());
        Date dateTime = new Date();
        course.setCreateTime(dateTime);
        course.setUpdateTime(dateTime);
        courseMapper.insert(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseMapper.selectCourseById(id);
    }

    @Override
    public ResultMap getCourseAndClazz(String username) {
        List<Course> courses = courseMapper.selectCoursesByCreateId(username);
        List<Long> courseIds = courses.stream().map(Course::getId).collect(Collectors.toList());
        List<KV> result = new ArrayList<>();
        for (Course c : courses) {
            KV kv = new KV();
            kv.setValue(c.getId());
            kv.setLabel(c.getName());
            result.add(kv);
        }
        List<Clazz> clazzes = clazzMapper.selectClazzsByCourseIds(courseIds);
        for (Clazz clazz : clazzes) {
            long courseId = clazz.getCourseId();
            for (KV kv : result) {
                if (kv.getValue() == courseId) {
                    if (kv.getChildren() != null) {
                        KV kv1 = new KV();
                        kv1.setValue(clazz.getId());
                        kv1.setLabel(clazz.getName());
                        kv.getChildren().add(kv1);
                    } else {
                        kv.setChildren(new ArrayList<>());
                        KV kv1 = new KV();
                        kv1.setValue(clazz.getId());
                        kv1.setLabel(clazz.getName());
                        kv.getChildren().add(kv1);
                    }
                }
            }
        }
        List<KV> r = result.stream().filter(kv -> kv.getChildren() != null).collect(Collectors.toList());
        ResultMap resultMap = new ResultMap();
        return resultMap.success().payloads(r);
    }
}
