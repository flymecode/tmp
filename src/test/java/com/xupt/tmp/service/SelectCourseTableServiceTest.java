package com.xupt.tmp.service;

import com.xupt.tmp.dto.courseTableDto.CourseTableCreate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SelectCourseTableServiceTest {

    @Autowired
    private SelectCourseTableService selectCourseTableService;

    @Test
    void getCourseTable() {
        List<Long> courseTable = selectCourseTableService.getCourseTable("02161017");
        System.out.println(courseTable);
    }

    @Test
    void addCourseTable() {
        CourseTableCreate courseTableCreate = new CourseTableCreate();
        courseTableCreate.setId(1L);
        courseTableCreate.setUsername("02161017");
        courseTableCreate.setCourseIds(Arrays.asList(1L, 2L, 3L, 4L));
        selectCourseTableService.addCourseTable(courseTableCreate);
    }
}
