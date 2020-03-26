package com.xupt.tmp.service.impl;

import com.xupt.tmp.dto.courseDto.CreateCourse;
import com.xupt.tmp.mapper.CourseMapper;
import com.xupt.tmp.model.Course;
import com.xupt.tmp.model.User;
import com.xupt.tmp.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

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
}
