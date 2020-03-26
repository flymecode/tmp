package com.xupt.tmp.dto.courseTableDto;

import lombok.Data;

import java.util.List;
@Data
public class CourseTableCreate {
    private Long id;
    private String username;
    private List<Long> courseIds;
}
