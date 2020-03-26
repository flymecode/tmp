package com.xupt.tmp.service.impl;

import com.xupt.tmp.dto.contestDto.ContestCreate;
import com.xupt.tmp.mapper.ContestMapper;
import com.xupt.tmp.model.Contest;
import com.xupt.tmp.service.ContestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ContestServiceImpl implements ContestService {

    @Autowired
    private ContestMapper contestMapper;

    @Override
    public void createContest(ContestCreate contestCreate) throws ParseException {
        Contest contest = contestBuild(contestCreate);
        contestMapper.insert(contest);
    }

    @Override
    public boolean updateContest(Contest contest) {
        return false;
    }

    @Override
    public boolean deleteContest(long id) {
        return contestMapper.deleteContest(id) > 0;
    }

    @Override
    public List<Contest> getContestsByCourseId(List<Long> courseId) {
        return contestMapper.selectContestsByCourseId(courseId);
    }


    private Contest contestBuild(ContestCreate contestCreate) throws ParseException {
        Contest contest = new Contest();
        contest.setCourseId(contestCreate.getCourseId());
        contest.setOperationId(contestCreate.getCreateId());
        contest.setQuestionPaperId(contestCreate.getId());
        contest.setType(contestCreate.getType());
        contest.setQuestions(contestCreate.getQuestions().toString());
        contest.setTitle(contestCreate.getTitle());
        List<String> time = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        for (String s : contestCreate.getTime()) {
            time.add(s.replace("Z", "UTC"));
        }
        contest.setName(contestCreate.getName());
        contest.setStartTime(format.parse(time.get(0)));
        contest.setEndTime(format.parse(time.get(1)));
        contest.setState(0);
        contest.setCreateTime(new Date());
        contest.setUpdateTime(new Date());
        return contest;
    }
}
