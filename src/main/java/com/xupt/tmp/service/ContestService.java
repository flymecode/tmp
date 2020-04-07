package com.xupt.tmp.service;

import com.xupt.tmp.dto.contestDto.ContestAnswer;
import com.xupt.tmp.dto.contestDto.ContestCreate;
import com.xupt.tmp.dto.contestDto.ContestResult;
import com.xupt.tmp.model.Contest;
import com.xupt.tmp.model.Grade;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

public interface ContestService {
    void createContest(ContestCreate contestCreate, HttpServletRequest request) throws ParseException;

    boolean updateContest(Contest contest);

    boolean deleteContest(long id);

    List<ContestResult> getContestsByCourseId(List<Long> courseId);

    void updateContestState(Long id, int state);

    void commitContest(ContestAnswer contestAnswer, String username);

    boolean getContestRecode(Long id, String username);

    List<ContestResult> getContestByCreateId(HttpServletRequest request);

    List<Grade> getContestRecords(Long id);
}
