package com.xupt.tmp.dto.replyDto;

import lombok.Data;

import java.util.Date;

@Data
public class CreateReply {
    /**
     * 班级id/课程id
     */
    private long[] value;
    /**
     * 原因
     */
    private String reason;

    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 类型 1-请假 2-申诉
     */
    private int type;
}
