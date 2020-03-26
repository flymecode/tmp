package com.xupt.tmp.mapper;

import com.xupt.tmp.model.Contest;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface ContestMapper {

    int insert(Contest contest);

    List<Contest> selectContestsByCourseId(List<Long> list);

    List<Contest> selectContestsById(List<Long> list);

    @Delete({"delete from contest where id = #{id}"})
    int deleteContest(long id);
}
