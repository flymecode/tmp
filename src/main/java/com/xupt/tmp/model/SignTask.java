package com.xupt.tmp.model;

import lombok.Data;

import java.util.Date;

@Data
public class SignTask {
    /**
     * 签到id
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
     * 创建人
     */
    private String createId;
    /**
     * 班级Id
     */
    private long clazzId;
    /**
     * 班级名称
     */
    private String clazzName;
    /**
     * 签到名称
     */
    private String name;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
}
