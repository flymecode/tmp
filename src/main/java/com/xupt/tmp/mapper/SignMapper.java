package com.xupt.tmp.mapper;

import com.xupt.tmp.model.SignRecord;
import com.xupt.tmp.model.SignTask;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface SignMapper {
    int insertSignTask(SignTask signTask);

    int insertSignRecord(List<SignRecord> signRecord);

    @Update({"update sign_record set status = 1, sign_time = #{date} where username = #{username}"})
    int updateSignRecord(@Param("username") String username, @Param("date") Date date);

    @Select({"select * from sign_record where sign_id = #{id}"})
    List<SignRecord> selectSignRecords(@Param("id") Long id);

    @Select({"select * from sign_record where sign_id = #{id} and username = #{username}"})
    SignRecord selectSignRecord(@Param("id") Long id ,@Param("username") String username);

    @Select({"SELECT sign_task.*,course.name as courseName,clazz.name as clazzName from sign_task,course,clazz where sign_task.create_id = #{username} and course.id=sign_task.course_id and clazz.id=sign_task.clazz_id;"})
    List<SignTask> selectSignTasks(String username);

    @Select({"select `id` from sign_task where course_id = #{courseId} and clazz_id = #{clazzId}"})
    List<Long> selectSignTasksByCourseIdAndClazzId(@Param("courseId") long courseId, @Param("clazzId") long clazzId);
}
