package com.xupt.tmp.service.impl;

import com.xupt.tmp.dto.gradeExtDto.GradeExtQuery;
import com.xupt.tmp.mapper.GradeExtMapper;
import com.xupt.tmp.model.GradeExt;
import com.xupt.tmp.model.Reply;
import com.xupt.tmp.service.GradeExtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeExtServiceImpl implements GradeExtService {
    @Autowired
    private GradeExtMapper gradeExtMapper;

    @Override
    public List<Reply> getGradeExts(String username, GradeExtQuery query) {
        return gradeExtMapper.selectGradeExts(username);
    }

    @Override
    public void createGradeExt(GradeExt gradeExt) {
        gradeExtMapper.insert(gradeExt);
    }

    @Override
    public void updateGradeExt(GradeExt gradeExt) {
        gradeExtMapper.updateGradeExt(gradeExt);
    }
}
