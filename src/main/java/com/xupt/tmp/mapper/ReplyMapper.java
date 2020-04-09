package com.xupt.tmp.mapper;

import com.xupt.tmp.model.Reply;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ReplyMapper {

    // TODO 创建申请/ 班级/课程/老师
    int insert(Reply reply);

    @Select({"select * from reply where teacher_id = #{username}"})
    List<Reply> selectReply(String username);

    @Update({"update reply set agree=#{agree} where id = #{id}"})
    int updateReply(Reply reply);
}
