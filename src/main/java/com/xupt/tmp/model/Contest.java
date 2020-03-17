package com.xupt.tmp.model;

import lombok.Data;
import org.joda.time.DateTime;

@Data
/**
 * 测试模型
 */
public class Contest {

    private long id;
    /**
     * 测试名称
     */
    private String title;
    /**
     * 总成绩
     */
    private int totalScore;
    /**
     * 课程ID
     */
    private long courseId;
    /**
     * 试卷ID
     */
    private long questionPaperId;
    /**
     * 操作人ID
     */
    private long operationId;

    /**
     * 创建时间
     */
    private DateTime createTime;
    /**
     * 更新时间
     */
    private DateTime updateTime;
    /**
     * 测试开始时间
     */
    private DateTime startTime;
    /**
     * 测试结束时间
     */
    private DateTime endTime;
    /**
     *  进行状态:0表示未开始,1表示进行中,2表示考试已经结束,3表示该考试已经完成批卷
     */
    private int state;
}
