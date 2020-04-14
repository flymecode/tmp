package com.xupt.tmp.service;

import com.xupt.tmp.dto.gradeDto.*;
import com.xupt.tmp.model.Grade;

import java.util.List;

public interface GradeService {
    void addGrade(Grade grade);

    GradeResult getGradeInfo(Long id);

    void commitGrade(GradeUpdate gradeUpdate);

    List<GradeSum> getGradeSum(GradeQuery query);

    List<GradeMyResult> getMyGrade(String username, int ruleId);
}
