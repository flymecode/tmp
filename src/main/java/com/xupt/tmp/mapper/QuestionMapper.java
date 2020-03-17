package com.xupt.tmp.mapper;

import com.xupt.tmp.dto.questionDto.QueryQuestionParam;
import com.xupt.tmp.model.Question;

import java.util.List;

public interface QuestionMapper {

    List<Question> getQuestionsByConditions(QueryQuestionParam queryQuestionParam);

    List<Question> selectQuestionByIds(List<Long> questionIds);
}
