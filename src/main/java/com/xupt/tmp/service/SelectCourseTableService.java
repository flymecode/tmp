package com.xupt.tmp.service;

import com.xupt.tmp.dto.courseTableDto.CourseTableCreate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SelectCourseTableService {
    /**
     * 根据学号获得课程信息
     */
    List<Long> getCourseTable(String username);

    void addCourseTable(CourseTableCreate courseTableCreate);
}
