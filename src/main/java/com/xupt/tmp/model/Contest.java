package com.xupt.tmp.model;

import lombok.Data;
import org.joda.time.DateTime;

@Data
/**
 * 测试模型
 */
public class Contest {

    private long id;
    private String title;
    private int totalScore;
    private long courseId;
    private long questionPaperId;
    private long operationId;
    private DateTime createTime;
    private DateTime updateTime;
    private DateTime startTime;
    private DateTime endTime;
    /**
     *  进行状态:0表示未开始,1表示进行中,2表示考试已经结束,3表示该考试已经完成批卷
     */
    private int state;
}
