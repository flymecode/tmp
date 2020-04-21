package com.xupt.tmp.mapper;

import com.xupt.tmp.dto.noticeDto.NoticeQuery;
import com.xupt.tmp.model.Notice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface NoticeMapper {

    int inster(Notice notice);

    List<Notice> selectNotices(NoticeQuery query);

    @Update({"update notice set content=#{content}, weight=#{weight}, course_id={course_id}, status=#{status} where id = #{id}"})
    int updateNotice(Notice notice);

    List<Notice> getNoticeByCourseIds(List<Long> courseIds);

    @Select({"select notice.*, course.name as courseName, clazz.name as clazzName " +
            "from notice,course,clazz " +
            "where notice.course_id = #{courseId} and clazz_id = #{clazzId} " +
            "and notice.course_id = course.id and notice.clazz_id = clazz.id"})
    List<Notice> selectNoticesByCourseIdAndClazzId(@Param("courseId") long courseId, @Param("clazzId") long clazzId);

    @Delete({"delete from notice where id = #{id}"})
    int delete(@Param("id") Long id);
}
