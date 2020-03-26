package com.xupt.tmp.controller;

import com.xupt.tmp.dto.ResultMap;
import com.xupt.tmp.model.Grade;
import com.xupt.tmp.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    public ResponseEntity commitContest(Grade grade) {
        gradeService.addGrade(grade);
        return ResponseEntity.ok(new ResultMap().success());
    }
}
