package com.xupt.tmp.service;

import com.xupt.tmp.dto.gradeExtDto.GradeExtQuery;
import com.xupt.tmp.model.GradeExt;
import com.xupt.tmp.model.Reply;

import java.util.List;

public interface GradeExtService {
    List<Reply> getGradeExts(String username, GradeExtQuery query);

    void createGradeExt(GradeExt gradeExt);

    void updateGradeExt(GradeExt gradeExt);
}
