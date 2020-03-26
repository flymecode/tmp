package com.xupt.tmp.dto.questionDto;

import lombok.Data;

@Data
public class QuestionContest {
    /**
     * 试题id
     */
    private long id;
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
     * 分值
     */
    private int score;
}
