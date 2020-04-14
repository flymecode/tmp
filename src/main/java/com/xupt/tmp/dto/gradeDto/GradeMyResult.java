package com.xupt.tmp.dto.gradeDto;

import com.xupt.tmp.model.Contest;
import com.xupt.tmp.model.GradeExt;
import com.xupt.tmp.model.SignRecord;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GradeMyResult {

    /**
     * 班级id
     */
    private long clazzId;
    /**
     * 课程id
     */
    private long courseId;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 班级名称
     */
    private String clazzName;
    /**
     * 签到成绩
     */
    private int signGrade;
    /**
     * 测试成绩
     */
    private int contestGrade;
    /**
     * 作业成绩
     */
    private int homeworkGrade;
    /**
     * 互动成绩
     */
    private int othersGrade;

    private List<Contest> contests = new ArrayList<>();
    private List<Contest> homework = new ArrayList<>();
    private List<SignRecord> signRecords = new ArrayList<>();
    private List<GradeExt> gradeExts = new ArrayList<>();
    private List<ContestAndGradeRelation> contestRelations = new ArrayList<>();
    private List<ContestAndGradeRelation> homeworkRelations = new ArrayList<>();

    /**
     * 总成绩
     */
    private int result;

    private int contestGradeSum = 0;

    private int homeworkGradeSum = 0;

    public void addContests(List<Contest> contests) {
        if (contests != null) {
            for (Contest contest : contests) {
                if (contest.getType() == 1) {
                    this.contests.add(contest);
                    this.contestGradeSum += contest.getPaperScore();
                } else {
                    this.homework.add(contest);
                    this.homeworkGradeSum += contest.getPaperScore();
                }
            }
        }
    }
}
