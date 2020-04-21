package com.xupt.tmp.mapper;

import com.xupt.tmp.dto.replyDto.ReplyNoAgree;
import com.xupt.tmp.model.Reply;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ReplyMapper {

    int insert(Reply reply);

    @Select({"select reply.*,course.name as courseName,clazz.name as clazzName " +
            "from reply,course,clazz " +
            "where reply.teacher_id = #{username} " +
            "and reply.agree = 0 " +
            "and course.id = reply.course_id " +
            "and clazz.id = reply.clazz_id"})
    List<Reply> selectReply(String username);

    @Update({"update reply set agree=1 where id = #{id}"})
    int updateReplyAgree(@Param("id") long id);

    @Update({"update reply set message=#{message} where id = #{id}"})
    void updateReply(ReplyNoAgree replyAgree);
}
