package com.xupt.tmp.service;

import com.xupt.tmp.dto.courseDto.CreateCourse;
import com.xupt.tmp.model.Course;
import com.xupt.tmp.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class CourseServiceTest {
    @Autowired
    private CourseService courseService;

    @Test
    void getCourses() {
        List<Long> integers = Arrays.asList(1L, 2L, 3L, 4L);
        List<Course> courses = courseService.getCourses(integers);
        System.out.println(courses);
    }

    @Test
    void createCourse() {
         System.out.println("[1,2,3,4]".contains("3"));
         System.out.println("[1,2,3,4]".contains("5"));
    }

    @Test
    void addCourse() {
        for (int i = 3; i < 5; i++) {
            User user = new User();
            user.setUsername("2006000894");
            user.setName("李培");
            CreateCourse createCourse = new CreateCourse();
            createCourse.setDescription("是个很牛逼的课");
            createCourse.setName("数据结构");
            createCourse.setPeriod(32);
            createCourse.setId(i);
            courseService.addCourse(user, createCourse);
        }
    }
}
