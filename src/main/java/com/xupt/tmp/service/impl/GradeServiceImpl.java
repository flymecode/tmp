package com.xupt.tmp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xupt.tmp.dto.gradeDto.*;
import com.xupt.tmp.dto.questionDto.QuestionFResult;
import com.xupt.tmp.dto.userDto.UserUpload;
import com.xupt.tmp.mapper.*;
import com.xupt.tmp.model.*;
import com.xupt.tmp.service.GradeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private RuleMapper ruleMapper;

    @Autowired
    private SignMapper signMapper;

    @Autowired
    private ContestMapper contestMapper;

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public void addGrade(Grade grade) {
        GradeCreate gradeCreate = new GradeCreate();
        BeanUtils.copyProperties(grade, gradeCreate);
        gradeMapper.insert(grade);
    }

    @Override
    public GradeResult getGradeInfo(Long id) {
        Grade grade = gradeMapper.selectGradeRecodeById(id);
        String answerJson = grade.getAnswerJson();
        List<QARelation> qaRelations = JSONObject.parseArray(answerJson, QARelation.class);
        Map<Long, String> relations = qaRelations.stream().collect(Collectors.toMap(QARelation::getId, QARelation::getR));
        List<Long> questionIds = qaRelations.stream().map(QARelation::getId).collect(Collectors.toList());
        List<Question> questions = questionMapper.selectQuestionByIds(questionIds);
        List<QuestionFResult> results = questions.stream().map(q -> {
            QuestionFResult questionResult = new QuestionFResult();
            BeanUtils.copyProperties(q, questionResult);
            String r = relations.get(questionResult.getId());
            questionResult.setR(r);
            return questionResult;
        }).collect(Collectors.toList());
        GradeResult gradeResult = new GradeResult();
        BeanUtils.copyProperties(grade, gradeResult);
        gradeResult.setQuestionResults(results);
        return gradeResult;
    }

    @Override
    public void commitGrade(GradeUpdate gradeUpdate) {
        Grade grade = new Grade();
        List<GradeUpdate.Score> scores = gradeUpdate.getScores();
        int count = 0;
        for (GradeUpdate.Score score : scores) {
            count += score.getScore();
        }
        grade.setManulResult(count);
        grade.setResult(count + grade.getAutoResult());
        grade.setFinishTime(new Date());
        grade.setState(1);
        grade.setScores(scores.toString());
        grade.setId(gradeUpdate.getId());
        gradeMapper.updateGrade(grade);
    }

    @Override
    public List<GradeSum> getGradeSum(GradeQuery query) {
        int ruleId = query.getRuleId();
        long[] value = query.getValue();
        long courseId = value[0];
        long clazzId = value[1];

        //Rule rule = ruleMapper.selectRulesById(ruleId);
        Rule rule = new Rule();
        rule.setContestWeight(70);
        rule.setSignWeight(30);
        String studentsJson = clazzMapper.selectStudents(clazzId);
        List<UserUpload> students = JSONObject.parseArray(studentsJson, UserUpload.class);
        List<GradeSum> gradeSums = students.stream().map(s -> {
            GradeSum gradeSum = new GradeSum();
            BeanUtils.copyProperties(s, gradeSum);
            return gradeSum;
        }).collect(Collectors.toList());

        // 获取课程/班级所有测试
        List<Contest> contests = contestMapper.selectContestsByCourseIdAndClazzId(courseId, clazzId);
        // 获取课程/班级所有签到任务
        List<Long> signTaskIds = signMapper.selectSignTasksByCourseIdAndClazzId(courseId, clazzId);

        for (GradeSum gradeSum : gradeSums) {
            for (Contest contest : contests) {
                Grade grade = gradeMapper.selectGradeRecode(contest.getId(), gradeSum.getUsername());
                gradeSum.getGrades().add(grade);
            }
            for (Long signTaskId : signTaskIds) {
                SignRecord signRecords = signMapper.selectSignRecord(signTaskId, gradeSum.getUsername());
                gradeSum.getSignRecords().add(signRecords);
            }
        }

        int contestGradeSum = 0;
        // TODO 获取测试成绩
        for (Contest contest : contests) {
            contestGradeSum += 100;
        }
        for (GradeSum gradeSum : gradeSums) {
            List<SignRecord> signRecords = gradeSum.getSignRecords();
            int signTemp = 0;
            for (SignRecord signRecord : signRecords) {
                if (signRecord.getStatus() == 1) {
                    signTemp++;
                }
            }
            int signCount = signTaskIds.size();
            long signGrade = signTemp * rule.getSignWeight() / signCount;
            gradeSum.setSignGrade(signGrade);

            List<Grade> grades = gradeSum.getGrades();
            int contestGradeTemp = 0;
            for (Grade grade : grades) {
                contestGradeTemp += grade.getResult();
            }
            int contestGrade = contestGradeTemp * rule.getContestWeight() / contestGradeSum;
            gradeSum.setContestGrade(contestGrade);
            gradeSum.setResult(contestGrade + signGrade);
        }
        return gradeSums;
    }

}