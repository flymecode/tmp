package com.xupt.tmp.model;

import lombok.Data;

import java.util.Date;

@Data
public class QuestionPaper {
    /**
     * 试卷Id
     */
    private long id;
    /**
     * 课程Id
     */
    private long courseId;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 创建人Id
     */
    private String createId;
    /**
     * 试卷名称
     */
    private String name;
    /**
     * 试题
     */
    private String questions;
    /**
     * 试卷成绩
     */
    private int paperScore;
    /**
     * 额外字段
     */
    private int status;

    private Date createTime;
    private Date updateTime;

}
