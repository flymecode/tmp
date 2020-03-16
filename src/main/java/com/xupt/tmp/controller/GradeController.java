package com.xupt.tmp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/grade")
public class GradeController {

    public ResponseEntity grade(QueryGrade queryGrade) {
        return null;
    }
}
