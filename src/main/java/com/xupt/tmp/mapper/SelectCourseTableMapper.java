package com.xupt.tmp.mapper;

import com.xupt.tmp.model.SelectCourseTable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SelectCourseTableMapper {

    @Select({"select course_ids from select_course_table where `username` = #{username}"})
    String selectCourseTable(@Param("username") String username);

    void insert(SelectCourseTable selectCourseTable);

    @Update({"update select_course_table set course_ids = #{newCourseTable} where username = #{username}"})
    void update(@Param("username") String username, @Param("newCourseTable") String newCourseTable);
}
