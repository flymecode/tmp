package com.xupt.tmp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xupt.tmp.dto.gradeDto.*;
import com.xupt.tmp.dto.gradeExtDto.GradeExtQuery;
import com.xupt.tmp.dto.questionDto.QuestionFResult;
import com.xupt.tmp.dto.userDto.UserUpload;
import com.xupt.tmp.mapper.*;
import com.xupt.tmp.model.*;
import com.xupt.tmp.service.GradeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private GradeExtMapper gradeExtMapper;

    @Autowired
    private UCCMapper uccMapper;

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
        Rule rule = ruleMapper.selectRulesById(ruleId);
        String studentsJson = clazzMapper.selectStudents(clazzId);
        List<UserUpload> students = JSONObject.parseArray(studentsJson, UserUpload.class);
        List<GradeSum> gradeSums = students.stream().map(s -> {
            GradeSum gradeSum = new GradeSum();
            BeanUtils.copyProperties(s, gradeSum);
            // 添加平时成绩
            List<GradeExt> list = gradeExtMapper.selectGradeExts(s.getUsername());
            if (list != null) {
                int others = list.stream().map(GradeExt::getGrade).reduce(0, Integer::sum);
                gradeSum.setOthers(others);
                gradeSum.setGradeExts(list);
            }
            return gradeSum;
        }).collect(Collectors.toList());

        // 获取课程/班级所有测试
        List<Contest> works = contestMapper.selectContestsByCourseIdAndClazzId(courseId, clazzId);
        List<Contest> contests = new ArrayList<>();
        List<Contest> homework = new ArrayList<>();

        for (Contest work : works) {
            if (work.getType() == 1) {
                contests.add(work);
            } else {
                homework.add(work);
            }
        }

        // 获取课程/班级所有签到任务
        List<Long> signTaskIds = signMapper.selectSignTasksByCourseIdAndClazzId(courseId, clazzId);
        for (GradeSum gradeSum : gradeSums) {
            // 考试
            for (Contest contest : contests) {
                Grade grade = gradeMapper.selectGradeRecode(contest.getId(), gradeSum.getUsername());
                if (grade != null) {
                    gradeSum.getGrades().add(grade);
                }
            }
            // 签到
            for (Long signTaskId : signTaskIds) {
                SignRecord signRecords = signMapper.selectSignRecord(signTaskId, gradeSum.getUsername());
                if (signRecords != null) {
                    gradeSum.getSignRecords().add(signRecords);
                }
            }
            // 平时作业
            for (Contest contest : homework) {
                Grade grade = gradeMapper.selectGradeRecode(contest.getId(), gradeSum.getUsername());
                if (grade != null) {
                    gradeSum.getHomeworkGrades().add(grade);
                }
            }

        }

        int contestGradeSum = 0;
        int homeworkGradeSum = 0;
        for (Contest contest : contests) {
            contestGradeSum += contest.getPaperScore();
        }
        for (Contest contest : homework) {
            homeworkGradeSum += contest.getPaperScore();
        }
        for (GradeSum gradeSum : gradeSums) {
            // 平时
            List<SignRecord> signRecords = gradeSum.getSignRecords();
            // 签到次数
            long signTemp = signRecords.stream().filter(signRecord -> signRecord.getStatus() == 1).count();
            int signCount = signTaskIds.size();
            long signGrade = signTemp * rule.getSignWeight() / signCount;
            gradeSum.setSignGrade(signGrade);

            // 考试
            List<Grade> grades = gradeSum.getGrades();
            int contestGradeTemp = 0;
            for (Grade grade : grades) {
                contestGradeTemp += grade.getResult();
            }
            int contestGrade = 0;
            if (contestGradeSum != 0) {
                contestGrade = contestGradeTemp * rule.getContestWeight() / contestGradeSum;
            }
            gradeSum.setContestGrade(contestGrade);

            // 平时
            List<Grade> homeworkGrades = gradeSum.getHomeworkGrades();
            int homeworkGradeTemp = 0;
            for (Grade homeworkGrade : homeworkGrades) {
                homeworkGradeTemp += homeworkGrade.getResult();
            }
            int homeworkGrade = 0;
            if (homeworkGradeSum != 0) {
                homeworkGrade = homeworkGradeTemp * rule.getHomeworkWeight() / homeworkGradeSum;
            }
            gradeSum.setHomeWorkGrade(homeworkGrade);
            // 设置总成绩
            gradeSum.setResult(contestGrade + signGrade + homeworkGrade + gradeSum.getOthers());
        }
        return gradeSums;
    }

    @Override
    public List<GradeMyResult> getMyGrade(String username, int ruleId) {
        Rule rule = ruleMapper.selectRulesById(ruleId);
        List<UCCRelation> uccRelations = uccMapper.selectRelation(username);

        List<GradeMyResult> results = uccRelations.stream().map(e -> {
            GradeMyResult result = new GradeMyResult();
            result.setClazzId(e.getClazzId());
            result.setCourseId(e.getCourseId());
            result.setCourseName(e.getCourseName());
            result.setClazzName(e.getClazzName());
            return result;
        }).collect(Collectors.toList());

        // 查询考试和作业
        for (GradeMyResult result : results) {
            List<Contest> tempContest = contestMapper.selectContestsByCourseIdAndClazzId(result.getCourseId(), result.getClazzId());
            result.addContests(tempContest);
            List<Contest> contests = result.getContests();
            List<ContestAndGradeRelation> contestRelations = result.getContestRelations();
            int tempContestScore = 0;
            for (Contest contest : contests) {
                Grade grade = gradeMapper.selectGradeRecode(contest.getId(), username);
                if (grade != null) {
                    tempContestScore += grade.getResult();
                    ContestAndGradeRelation gradeRelation = new ContestAndGradeRelation(contest, grade);
                    contestRelations.add(gradeRelation);
                }
            }
            // 设置测试成绩
            if (result.getContestGradeSum() != 0) {
                result.setContestGrade(tempContestScore * rule.getContestWeight() / result.getContestGradeSum());
            }
            List<Contest> homework = result.getHomework();
            List<ContestAndGradeRelation> homeworkRelations = result.getHomeworkRelations();
            int homeworkScore = 0;
            for (Contest contest : homework) {
                Grade grade = gradeMapper.selectGradeRecode(contest.getId(), username);
                homeworkScore += grade.getResult();
                ContestAndGradeRelation gradeRelation = new ContestAndGradeRelation(contest, grade);
                homeworkRelations.add(gradeRelation);
            }
            // 设置作业成绩
            if (result.getContestGradeSum() != 0) {
                result.setContestGrade(homeworkScore * rule.getHomeworkWeight() / result.getContestGradeSum());
            }
            // 查询签到
            List<Long> signIds = signMapper.selectSignTasksByCourseIdAndClazzId(result.getCourseId(), result.getClazzId());
            int size = 0;
            int tempSign = 0;
            List<SignRecord> signRecords = result.getSignRecords();
            for (Long signId : signIds) {
                SignRecord signRecord = signMapper.selectSignRecord(signId, username);
                if (signRecord != null) {
                    signRecords.add(signRecord);
                    size++;
                    if (signRecord.getStatus() == 1) {
                        tempSign++;
                    }
                    if (size != 0) {
                        result.setSignGrade(tempSign * rule.getSignWeight() / size);
                    }
                }
            }
            GradeExtQuery query = new GradeExtQuery();
            query.setUsername(username);
            query.setClazzId(result.getClazzId());
            query.setCourseId(result.getCourseId());
            List<GradeExt> list = gradeExtMapper.selectGradeExtsInfo(query);
            result.setGradeExts(list);
            int tempGradeExt = 0;
            int gradeExtSize = list.size();
            for (GradeExt gradeExt : list) {
                tempGradeExt += gradeExt.getGrade();
            }
            if (gradeExtSize != 0) {
                result.setOthersGrade(tempGradeExt * rule.getOthersWeight() / gradeExtSize);
            }

        }
        return results;
    }
}
