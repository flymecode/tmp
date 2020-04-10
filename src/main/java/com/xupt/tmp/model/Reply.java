package com.xupt.tmp.model;

import lombok.Data;

import java.util.Date;

/**
 * 申请（请假\销假）
 */
@Data
public class Reply {
    /**
     * id
     */
    private long id;
    /**
     * 教师Id
     */
    private String teacherId;
    /**
     * 学号
     */
    private String username;
    private String courseName;
    private String clazzName;
    /**
     * 姓名
     */
    private String name;
    /**
     * 班级id
     */
    private long clazzId;
    /**
     * 原因
     */
    private String reason;
    /**
     * 信息
     */
    private String message;
    /**
     * 课程id
     */
    private long courseId;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 类型
     */
    private int type;
    /**
     * 是否通过 0-初始化 1-同意 2-不同意
     */
    private Boolean agree;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 创建时间
     */
    private Date createTime;
}
