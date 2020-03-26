package com.xupt.tmp.model;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class SignTask {
    /**
     * 签到id
     */
    private int id;
    /**
     * 课程Id
     */
    private long courseId;
    /**
     * 签到名称
     */
    private String name;
    /**
     * 创建时间
     */
    private DateTime createTime;
    /**
     * 开始时间
     */
    private DateTime startTime;
    /**
     * 结束时间
     */
    private DateTime endTime;
}
