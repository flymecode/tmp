package com.xupt.tmp.model;

import lombok.Data;

import java.util.Date;

@Data
/**
 * 测试模型
 */
public class Contest {

    /**
     * 测试Id
     */
    private long id;
    /**
     * 测试名称
     */
    private String title;
    /**
     * 课程ID
     */
    private long courseId;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 班级Id
     */
    private long clazzId;
    /**
     * 班级名称
     */
    private String clazzName;
    /**
     * 操作人ID
     */
    private String operationId;
    /**
     * 试卷ID
     */
    private long questionPaperId;
    /**
     * 试卷名称
     */
    private String name;
    /**
     * 试题
     */
    private String questions;
    /**
     * 测试类型
     */
    private int type;
    /**
     * 测试开始时间
     */
    private Date startTime;
    /**
     * 测试结束时间
     */
    private Date endTime;
    /**
     *  进行状态:0表示未开始,1表示进行中,2表示考试已经结束,3表示该考试已经完成批卷
     */
    private int state;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
