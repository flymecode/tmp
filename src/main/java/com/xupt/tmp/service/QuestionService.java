package com.xupt.tmp.service;

import com.xupt.tmp.dto.ResultMap;

public interface QuestionService {
    /**
     * 根据试题类型查询题目
     */
    ResultMap getQuestionsByType(int type);

    ResultMap updateQuestionById(long id);

    ResultMap deleteQuestionById(long id);
}
