package com.xupt.tmp.model;

import lombok.Data;

/**
 * 额外成绩
 */
@Data
public class GradeExt{

    /**
     * id
     */
    private long id;
    /**
     * 班级id
     */
    private long clazzId;
    /**
     * 课程id
     */
    private long courseId;
    /**
     * 标题
     */
    private String title;
    /**
     * 学号
     */
    private String username;
    /**
     * 姓名
     */
    private String name;
    /**
     * 成绩
     */
    private int grade;
    /**
     * 类型
     */
    private int type;
}
