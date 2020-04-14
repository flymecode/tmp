package com.xupt.tmp.mapper;

import com.xupt.tmp.model.UCCRelation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UCCMapper {
    @Insert("insert into ucc_relation (course_id,clazz_id,username,name) values(#{courseId},#{id},#{username},#{name})")
    int insert(Long courseId, long id, String username, String name);

    @Select({"select ucc_relation.*,clazz.name as clazzName,course.name as courseName from ucc_relation, course,clazz " +
            "where ucc_relation.username = #{username} and ucc_relation.clazz_id = clazz.id and ucc_relation.course_id = course.id"})
    List<UCCRelation> selectRelation(String username);
}
