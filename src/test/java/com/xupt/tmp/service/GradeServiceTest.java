package com.xupt.tmp.service;

import com.xupt.tmp.dto.gradeDto.GradeQuery;
import com.xupt.tmp.dto.gradeDto.GradeResult;
import com.xupt.tmp.dto.gradeDto.GradeSum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class GradeServiceTest {

    @Autowired
    private GradeService gradeService;

    @Test
    void addGrade() {
    }

    @Test
    void getGradeInfo() {
        GradeResult gradeInfo = gradeService.getGradeInfo(1L);
        System.out.println(gradeInfo);
    }

    @Test
    void getGradeSum() {
        GradeQuery query = new GradeQuery();
        List<GradeSum> gradeSum = gradeService.getGradeSum(query);
        System.out.println(gradeSum);

    }
}
