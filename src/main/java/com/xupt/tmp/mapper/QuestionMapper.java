package com.xupt.tmp.mapper;

import com.xupt.tmp.dto.questionDto.QuestionQueryParam;
import com.xupt.tmp.model.Question;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface QuestionMapper {

    List<Question> getQuestionsByConditions(QuestionQueryParam questionQueryParam);

    List<Question> selectQuestionByIds(List<Long> questionIds);

    void insert(Question question);

    @Delete({"delete from `question` where `id` = #{id}"})
    void deleteQuestionById(long id);
}
