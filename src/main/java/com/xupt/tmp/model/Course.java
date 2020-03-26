package com.xupt.tmp.model;

import lombok.Data;

import java.util.Date;

@Data
public class Course {
    /**
     * 课程id
     */
    private long id;
    /**
     * 课程创建人Id
     * User(username)
     */
    private String createId;
    /**
     * 创建人姓名
     */
    private String createName;
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
     * 0 - 正常
     * 1 - 下架
     */
    private int status;
    /**
     * 课程描述
     */
    private String description;

    private Date createTime;
    private Date updateTime;
}
