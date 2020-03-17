package com.xupt.tmp.model;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class Question {
    private long id;
    /**
     * 课程id
     */
    private long courseId;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 题目类型 QuestionTypeEnum
     * 0 表示单项选择题, 1表示多项选择题, 2表示问答题, 3表示编程题'
     */
    private int questionType;
    /**
     * 选项
     */
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    /**
     * 答案
     */
    private String answer;
    /**
     * 没写
     */
    private String parse;
    /**
     * 题目分值
     */
    private long score;
    /**
     * 困难度
     */
    private int difficulty;

    private int state;
    /**
     * 标签
     */
    private String tag;

    private DateTime createTime;
    private DateTime updateTime;

}
