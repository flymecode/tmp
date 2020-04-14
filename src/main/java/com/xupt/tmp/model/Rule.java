package com.xupt.tmp.model;

import lombok.Data;

@Data
public class Rule {

    /**
     * id
     */
    private long id;
    /**
     * 班级
     */
    private long clazzId;
    /**
     * 课程
     */
    private long courseId;
    /**
     * 模版名称
     */
    private String name;
    /**
     * 测试比重
     */
    private int contestWeight;
    /**
     * 签到比重
     */
    private int signWeight;

    /**
     * 平时作业比重
     */
    private int homeworkWeight;

    /**
     * 平时成绩比重
     */
    private int othersWeight;

    /**
     * 学生正在使用的比例
     */
    private boolean use;

    /**
     * 创建人
     */
    private String username;
}
