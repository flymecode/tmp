package com.xupt.tmp.service;

import com.xupt.tmp.dto.gradeDto.GradeQuery;
import com.xupt.tmp.dto.gradeDto.GradeResult;
import com.xupt.tmp.dto.gradeDto.GradeSum;
import com.xupt.tmp.dto.gradeDto.GradeUpdate;
import com.xupt.tmp.model.Grade;

import java.util.List;

public interface GradeService {
    void addGrade(Grade grade);

    GradeResult getGradeInfo(Long id);

    void commitGrade(GradeUpdate gradeUpdate);

    List<GradeSum> getGradeSum(GradeQuery query);
}
