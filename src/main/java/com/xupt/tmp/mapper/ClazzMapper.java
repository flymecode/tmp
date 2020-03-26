package com.xupt.tmp.mapper;

import com.xupt.tmp.model.Clazz;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ClazzMapper {
    @Select({"select `id`,`name` from clazz where course_id = #{courseId}"})
    List<Clazz> selectClazzs(@Param("courseId") long courseId);

    int insert(Clazz clazz);

    @Select({"select `students` from clazz where id = #{clazzId}"})
    String selectStudents(@Param("clazzId") long clazzId);
}
