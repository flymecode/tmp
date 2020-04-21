package com.xupt.tmp.mapper;

import com.xupt.tmp.dto.contestDto.ContestQuery;
import com.xupt.tmp.model.Contest;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ContestMapper {

    int insert(Contest contest);

    List<Contest> selectContestsByCourseId(List<Long> list);

    List<Contest> selectContestsById(List<Long> list);

    @Delete({"delete from contest where id = #{id}"})
    int deleteContest(long id);

    @Update({"UPDATE contest set state = #{state} where id=#{id}"})
    int updateContestState(@Param("id") Long id, @Param("state") int state);


    @Select("select * from contest where id=#{id} ")
    Contest selectContest(@Param("id") long id);

    @Select("select * from contest where course_id=#{courseId} and clazz_id=#{clazzId}")
    List<Contest> selectContestsByCourseIdAndClazzId(@Param("courseId") long courseId, @Param("clazzId") long clazzId);

    List<Contest> getContestByCreateId(ContestQuery query);
}
