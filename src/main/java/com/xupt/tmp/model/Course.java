package com.xupt.tmp.model;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class Course {
    /**
     * 课程id
     */
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
     * 0 - 正常
     * 1 - 下架
     */
    private int state;
    /**
     * 题目数量
     */
    private int questionNum;
    /**
     * 课程描述
     */
    private String description;
    private DateTime createTime;
    private DateTime updateTime;
}
