package com.xupt.tmp.service;

import com.xupt.tmp.dto.gradeExtDto.GradeExtQuery;
import com.xupt.tmp.dto.gradeExtDto.GradeExtResult;
import com.xupt.tmp.model.GradeExt;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface GradeExtService {
    List<GradeExtResult> getGradeExts(GradeExtQuery query, HttpServletRequest request);

    void createGradeExt(GradeExt gradeExt);

    void updateGradeExt(GradeExt gradeExt);

    List<GradeExt> getGradeExtsInfo(GradeExtQuery query);

    void deleteGradeExt(long id);
}
