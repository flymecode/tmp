package com.xupt.tmp.mapper;

import com.xupt.tmp.model.QuestionPaper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface QuestionPaperMapper {

    int insert(QuestionPaper questionPaper);

    @Select({"select * from question_paper where create_id = #{username}"})
    List<QuestionPaper> selectPaperByCreateId(String username);

    @Select({"select * from question_paper where id = #{id}"})
    QuestionPaper selectPaperById(long id);

    QuestionPaper updatePaperByCreateId();
}
