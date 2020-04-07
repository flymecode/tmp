package com.xupt.tmp.model;

import lombok.Data;

import java.util.Date;

/**
 * 通知消息
 */
@Data
public class Notice {
    /**
     * 通知id
     */
    private long id;
    /**
     * 标题
     */
    private String name;
    /**
     * 课程Id
     */
    private long courseId;

    private String courseName;
    /**
     * 班级Id
     */
    private long clazzId;

    private String clazzName;
    /**
     * 文本内容
     */
    private String content;
    /**
     * 操作人id
     */
    private String createId;
    /**
     * 状态
     * 0 - 展示
     * 1 - 下架
     */
    private int status;
    /**
     * 权重
     */
    private int weight;

    private Date updateTime;
    private Date createTime;
}
