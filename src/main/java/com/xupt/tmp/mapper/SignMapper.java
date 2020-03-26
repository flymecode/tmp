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

    @Select({"select * from sign_record where username = #{username}"})
    List<SignRecord> selectSignRecords(@Param("username") String username);
}
