package com.xupt.tmp.mapper;

import com.xupt.tmp.model.Notice;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface NoticeMapper {

    int inster(Notice notice);

    @Select({"select notice.*, course.name as courseName, clazz.name as clazzName from notice,course,clazz where notice.create_id = #{createId} and notice.course_id = course.id and notice.clazz_id = clazz.id"})
    List<Notice> selectNotices(@Param("createId") String createId);

    @Update({"update notice set content=#{content}, weight=#{weight}, course_id={course_id}, status=#{status} where id = #{id}"})
    int updateNotice(Notice notice);

    List<Notice> getNoticeByCourseIds(List<Long> courseIds);
}
