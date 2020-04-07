package com.xupt.tmp.controller;

import com.xupt.tmp.annotion.CurrentUser;
import com.xupt.tmp.common.Consts;
import com.xupt.tmp.dto.ResultMap;
import com.xupt.tmp.dto.courseDto.CreateCourse;
import com.xupt.tmp.model.Course;
import com.xupt.tmp.model.User;
import com.xupt.tmp.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(Consts.BASE_API_PATH + "/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity getCourses(@CurrentUser User user) {
        List<Course> courseList = courseService.getCourses(user.getUsername());
        return ResponseEntity.ok(new ResultMap().success().payloads(courseList));
    }

    @PostMapping
    public ResponseEntity addCourse(@CurrentUser User user, @RequestBody CreateCourse createCourse) {
        courseService.addCourse(user, createCourse);
        return ResponseEntity.ok(new ResultMap().success());
    }

    @GetMapping("/s")
    public ResponseEntity getCourse(List<Long> courseIds) {
        List<Course>  courses = courseService.getCourses(courseIds);
        return ResponseEntity.ok(new ResultMap().success().payloads(courses));
    }

    @GetMapping("/info")
    public ResponseEntity getCourseAndClazz(@CurrentUser User user) {
        ResultMap courseAndClazz = courseService.getCourseAndClazz(user.getUsername());
        return ResponseEntity.ok(courseAndClazz);
    }

}
