package com.xupt.tmp.mapper;

import com.xupt.tmp.model.Question;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface QuestionMapper {
    @Select({"select * from question where type = #{type}"})
    List<Question> getQuestionByType(@Param("type") int type);

}
