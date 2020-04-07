package com.xupt.tmp.mapper;

import com.xupt.tmp.model.Grade;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface GradeMapper {
    void insert(Grade grade);

    @Select("select * from grade where contest_id=#{id} and student_id=#{username}")
    Grade selectGradeRecode(@Param("id") Long id, @Param("username") String username);

    @Select("select grade.* from grade where contest_id=#{id}")
    List<Grade> selectGradeRecodesByIds(Long id);

    @Select("select * from grade where id=#{id}")
    Grade selectGradeRecodeById(@Param("id") Long id);

    @Update("update grade set manul_result = #{manulResult},result=#{result},finish_time=#{finishTime},state=#{state},scores=#{scores} where id = #{id}")
    int updateGrade(Grade grade);
}
