package com.xupt.tmp.dto.signDto;

import lombok.Data;

import java.util.Date;

@Data
public class SignCreate {
    private long id;
    /**
     * 课程Id
     */
    private long courseId;
    /**
     * 班级Id
     */
    private long clazzId;
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
