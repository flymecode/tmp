package com.xupt.tmp.mapper;

import com.xupt.tmp.model.Reply;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ReplyMapper {

    int insert(Reply reply);

    @Select({"select reply.*,course.name as courseName,clazz.name as clazzName " +
            "from reply,course,clazz " +
            "where reply.teacher_id = #{username} and reply.agree = 0 " +
            "and course.id = reply.course_id " +
            "and clazz.id = reply.clazz_id"})
    List<Reply> selectReply(String username);

    @Update({"update reply set agree=#{agree} where id = #{id}"})
    int updateReply(Reply reply);
}
