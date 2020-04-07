package com.xupt.tmp.dto.contestDto;

import com.xupt.tmp.model.Contest;
import lombok.Data;

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
    private long startTime;
    /**
     * 测试结束时间
     */
    private long endTime;
    /**
     * 当前时间
     */
    private long curTime;
    /**
     * 进行状态:0表示未开始,1表示进行中,2表示考试已经结束,3表示该考试已经完成批卷
     */
    private int state;
    private String clazzName;
    private String courseName;

    public ContestResult(Contest contest) {
        this.startTime = contest.getStartTime().getTime();
        this.endTime = contest.getEndTime().getTime();
        this.curTime = System.currentTimeMillis();
        this.id = contest.getId();
        this.title = contest.getTitle();
        this.name = contest.getName();
        this.type = contest.getType();
        this.questions = contest.getQuestions();
        this.questionPaperId = contest.getQuestionPaperId();
        this.state = contest.getState();
        this.clazzName = contest.getClazzName();
        this.courseName = contest.getCourseName();
    }

}
