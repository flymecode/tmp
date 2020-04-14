package com.xupt.tmp.dto.gradeDto;

import com.xupt.tmp.model.Contest;
import com.xupt.tmp.model.Grade;
import lombok.Data;

@Data
public class ContestAndGradeRelation {
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 班级名称
     */
    private String clazzName;
    /**
     * 试卷名称
     */
    private String name;
    /**
     * 总成绩
     */
    private int result;

    public ContestAndGradeRelation(Contest contest, Grade grade) {
        this.courseName = contest.getCourseName();
        this.clazzName = contest.getClazzName();
        this.name = contest.getName();
        this.result = grade.getAutoResult() + grade.getManulResult();
    }
}
