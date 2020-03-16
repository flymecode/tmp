package com.xupt.tmp.model;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class Question {
    private long id;
    private String title;
    private String content;
    /**
     * 题目类型
     * 0表示单项选择题,1表示多项选择题,2表示问答题,3表示编程题'
     */
    private int questionType;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;
    private String parse;
    private long courseId;
    private DateTime createTime;
    private DateTime updateTime;
    private long score;
    private int difficulty;
    private int state;
}
