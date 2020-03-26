package com.xupt.tmp.service;

import com.xupt.tmp.dto.contestDto.ContestCreate;
import com.xupt.tmp.model.Contest;

import java.text.ParseException;
import java.util.List;

public interface ContestService {
    void createContest(ContestCreate contestCreate) throws ParseException;

    boolean updateContest(Contest contest);

    boolean deleteContest(long id);

    List<Contest> getContestsByCourseId(List<Long> courseId);
}
