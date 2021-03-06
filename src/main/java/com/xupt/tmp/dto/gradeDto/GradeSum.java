package com.xupt.tmp.dto.gradeDto;

import com.xupt.tmp.model.Contest;
import com.xupt.tmp.model.Grade;
import com.xupt.tmp.model.GradeExt;
import com.xupt.tmp.model.SignRecord;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GradeSum {
    private String username;
    private String name;
    /**
     * 总成绩
     */
    private long result;
    private long signGrade;
    private long contestGrade;
    private long homeWorkGrade;
    private long others;

    private List<GradeExt> gradeExts = new ArrayList<>();
    private List<Grade> grades = new ArrayList<>();
    private List<Grade> homeworkGrades = new ArrayList<>();

    private List<Contest> contests = new ArrayList<>();
    private List<SignRecord> signRecords = new ArrayList<>();
}
