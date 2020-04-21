package com.xupt.tmp.mapper;

import com.xupt.tmp.dto.gradeExtDto.GradeExtQuery;
import com.xupt.tmp.model.GradeExt;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

public interface GradeExtMapper {
    @Select({"select * from grade_ext where username=#{username}"})
    List<GradeExt> selectGradeExts(String username);

    int insert(GradeExt gradeExt);

    @Update({"update grade_ext set title=#{title},grade=#{grade},type=#{type} where id=#{id}"})
    int updateGradeExt( GradeExt gradeExt);

    @Select({"select * from grade_ext where username = #{username} and clazz_id=#{clazzId} and course_id=#{courseId}"})
    List<GradeExt> selectGradeExtsInfo(GradeExtQuery query);

    @DeleteMapping({"delete from grade_ext where id = #{id}"})
    int deleteGradeExtById(@Param("id") long id);

    @Select({"SELECT IFNULL(sum(grade),0) from grade_ext where username = #{username} and course_id = #{courseId} and clazz_id = #{clazzId}"})
    int selectGradeSum(@Param("username") String username, @Param("courseId") long courseId, @Param("clazzId") long clazzId);
}
