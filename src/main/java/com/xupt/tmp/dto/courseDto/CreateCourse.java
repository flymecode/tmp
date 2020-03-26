package com.xupt.tmp.dto.courseDto;

import lombok.Data;

@Data
public class CreateCourse {
    private long id;
    /**
     * 课时
     */
    private int period;
    /**
     * 课程名称
     */
    private String name;
    /**
     * 课程图片展示
     */
    private String imgUrl;
    /**
     * 课程描述
     */
    private String description;
}
