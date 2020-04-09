package com.xupt.tmp.mapper;

import com.xupt.tmp.model.GradeExt;
import com.xupt.tmp.model.Reply;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GradeExtMapper {
    @Select({"select * from grade_ext where username=#{username}"})
    List<Reply> selectGradeExts(String username);

    int insert(GradeExt gradeExt);

    int updateGradeExt(GradeExt gradeExt);
}
