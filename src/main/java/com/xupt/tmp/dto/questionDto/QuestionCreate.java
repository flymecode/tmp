package com.xupt.tmp.dto.questionDto;

import lombok.Data;


@Data
public class QuestionCreate {
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
     * 解析
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
}
