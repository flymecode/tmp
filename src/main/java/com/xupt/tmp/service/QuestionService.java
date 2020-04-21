package com.xupt.tmp.service;

import com.github.pagehelper.PageInfo;
import com.xupt.tmp.dto.ResultMap;
import com.xupt.tmp.dto.questionDto.QuestionCreate;
import com.xupt.tmp.dto.questionDto.QuestionQueryParam;
import com.xupt.tmp.dto.questionDto.QuestionResult;
import com.xupt.tmp.model.Question;

import java.util.List;

public interface QuestionService {
    /**
     * 根据试题类型查询题目
     */
    PageInfo<Question> getQuestionsByConditions(QuestionQueryParam questionQueryParam);

    ResultMap updateQuestionById(long id);

    ResultMap deleteQuestionById(long id);

    List<QuestionResult> getQuestionsById(List<Long> list);

    void addQuestion(QuestionCreate questionCreate);
}
