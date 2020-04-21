package com.xupt.tmp.dto.replyDto;

import lombok.Data;

import java.util.Date;

@Data
public class ReplyCreate {
    /**
     * 签到id
     */
    private long signId;
    /**
     * 班级id/课程id
     */
    private long clazzId;

    private long courseId;
    /**
     * 原因
     */
    private String reason;

    /**
     * 开始时间
     */
    private Date[] date;
    /**
     * 类型 1-请假 2-申诉
     */
    private int type;
}
