package com.xupt.tmp.mapper;

import com.xupt.tmp.model.Course;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CourseMapper {

    void insert(Course course);

    @Select({"select * from course where `create_id` = #{createId}"})
    List<Course> selectCoursesByCreateId(@Param("createId") String createId);

    List<Course> selectCoursesByIds(List<Long> courseIds);

    @Select({"select * from course where `id` = #{id}"})
    Course selectCourseById(Long id);
}
