package com.xupt.tmp.service;

import com.xupt.tmp.dto.courseDto.CreateCourse;
import com.xupt.tmp.model.Course;
import com.xupt.tmp.model.User;

import java.util.List;

public interface CourseService {

    /**
     * 根据创建人id查找课程
     */
    List<Course> getCourses(String createId);
    /**
     * 根据课程id查找课程
     */
    List<Course> getCourses(List<Long> courseIds);

    void createCourse(CreateCourse createCourse);

    void addCourse(User user, CreateCourse createCourse);

    Course getCourseById(Long id);
}
