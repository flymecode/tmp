package com.xupt.tmp.controller;

import com.xupt.tmp.annotion.CurrentUser;
import com.xupt.tmp.model.User;
import com.xupt.tmp.service.QuestionPaperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/paper")
public class QuestionPaperController {

    @Autowired
    private QuestionPaperService questionPaperService;

    public ResponseEntity getPaper(@CurrentUser User user) {
        questionPaperService.getPaper(user.getUsername());
        return null;
    }
}
