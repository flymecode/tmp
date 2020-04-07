package com.xupt.tmp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xupt.tmp.common.Consts;
import com.xupt.tmp.dto.contestDto.ContestAnswer;
import com.xupt.tmp.dto.contestDto.ContestCreate;
import com.xupt.tmp.dto.contestDto.ContestResult;
import com.xupt.tmp.mapper.ContestMapper;
import com.xupt.tmp.mapper.GradeMapper;
import com.xupt.tmp.mapper.QuestionMapper;
import com.xupt.tmp.mapper.QuestionPaperMapper;
import com.xupt.tmp.model.*;
import com.xupt.tmp.service.ContestService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

@Service
@Slf4j
public class ContestServiceImpl implements ContestService {

    @Autowired
    private ContestMapper contestMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private GradeMapper gradeMapper;

    @Autowired
    private QuestionPaperMapper questionPaperMapper;

    @Override
    public void createContest(ContestCreate contestCreate, HttpServletRequest request) throws ParseException {
        Contest contest = contestBuild(contestCreate);
        User user = (User) request.getAttribute(Consts.CURRENT_USER);
        QuestionPaper questionPaper = questionPaperMapper.selectPaperById(contest.getQuestionPaperId());
        contest.setName(questionPaper.getName());
        contest.setQuestions(questionPaper.getQuestions());
        contest.setOperationId(user.getUsername());
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
    public List<ContestResult> getContestsByCourseId(List<Long> courseId) {
        List<Contest> contests = contestMapper.selectContestsByCourseId(courseId);
        List<ContestResult> contestResults = new ArrayList<>();
        for (Contest contest : contests) {
            ContestResult contestResult = new ContestResult(contest);
            contestResults.add(contestResult);
        }
        return contestResults;
    }

    @Override
    public void updateContestState(Long id, int state) {
        contestMapper.updateContestState(id, state);
    }

    @Override
    public void commitContest(ContestAnswer contestAnswer, String username) {
        List<ContestAnswer.Node> answer = contestAnswer.getAnswer();
        String q = contestAnswer.getQuesitons();
        List<Long> longs = JSONObject.parseArray(q, Long.class);

        List<Question> questions = questionMapper.selectQuestionByIds(longs);
        Map<Long, Question> map = new HashMap<>();
        for (Question question : questions) {
            if (question.getQuestionType() == 0) {
                map.put(question.getId(), question);
            }
        }
        int count = 0;
        for (ContestAnswer.Node node : answer) {
            String r = node.getR();
            if (StringUtils.isEmpty(r)) {
                continue;
            }
            long id = node.getId();
            Question question = map.get(id);
            if (question != null && r.equals(question.getAnswer())) {
                count += question.getScore();
            }
        }
        Grade grade = new Grade();
        grade.setAnswerJson(answer.toString());
        grade.setAutoResult(count);
        grade.setState(0);
        grade.setStudentId(username);
        grade.setContestId(contestAnswer.getContestId());
        grade.setCreateTime(new Date());
        gradeMapper.insert(grade);
    }

    @Override
    public boolean getContestRecode(Long id, String username) {
        Grade grade = gradeMapper.selectGradeRecode(id, username);
        if (grade != null) {
            return true;
        }
        return false;
    }

    @Override
    public List<ContestResult> getContestByCreateId(HttpServletRequest request) {
        User user = (User) request.getAttribute(Consts.CURRENT_USER);
        List<Contest> contestByCreateId = contestMapper.getContestByCreateId(user.getUsername());
        List<ContestResult> contestResults = new ArrayList<>();
        for (Contest contest : contestByCreateId) {
            ContestResult contestResult = new ContestResult(contest);
            contestResults.add(contestResult);
        }
        return contestResults;
    }

    @Override
    public List<Grade> getContestRecords(Long id) {
        return gradeMapper.selectGradeRecodesByIds(id);
    }


    private Contest contestBuild(ContestCreate contestCreate) throws ParseException {
        Contest contest = new Contest();
        Long[] value = contestCreate.getValue();
        contest.setCourseId(value[0]);
        contest.setClazzId(value[1]);
        contest.setQuestionPaperId(contestCreate.getQuestionPaperId());
        contest.setType(contestCreate.getType());
        contest.setTitle(contestCreate.getTitle());
        Long[] time = contestCreate.getTime();
        contest.setStartTime(new Date(time[0]));
        contest.setEndTime(new Date(time[1]));
        contest.setState(0);
        contest.setCreateTime(new Date());
        contest.setUpdateTime(new Date());
        return contest;
    }
}
