package com.xupt.tmp.model;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class Grade {
    /**
     * 成绩id
     */
    private long id;
    /**
     * 学号
     */
    private String studentId;
    /**
     * 测试id
     */
    private long contestId;
    /**
     * 总成绩
     */
    private int result;
    /**
     * 自动阅卷成绩
     */
    private int autoResult;
    /**
     * 人工成绩
     */
    private int manulResult;
    /**
     * 回答记录
     */
    private String answerJson;
    /**
     * 提交时间
     */
    private DateTime createTime;
    /**
     * 阅卷时间
     */
    private DateTime finishTime;
    /**
     * 是否批阅
     * 0 - 未批阅
     * 1- 批阅
     */
    private int state;
}
