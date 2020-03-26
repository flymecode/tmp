package com.xupt.tmp.dto.contestDto;

import lombok.Data;

import java.util.Date;

@Data
public class ContestResult {
    /**
     * 测试id
     */
    private long id;
    /**
     * 测试名称
     */
    private String title;
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

}
