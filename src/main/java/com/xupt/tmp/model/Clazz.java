package com.xupt.tmp.model;

import lombok.Data;

@Data
public class Clazz {
    /**
     * 班级id
     */
    private long id;
    /**
     * 课程id
     */
    private long courseId;
    /**
     * 课程名称
     */
    private String name;
    /**
     * 学生信息
     */
    private String students;
    /**
     * 班级人数
     */
    private int num;
}
