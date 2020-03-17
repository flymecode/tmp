package com.xupt.tmp.controller;

import com.xupt.tmp.common.Consts;
import com.xupt.tmp.dto.ResultMap;
import com.xupt.tmp.dto.questionDto.QueryQuestionParam;
import com.xupt.tmp.model.Question;
import com.xupt.tmp.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = Consts.BASE_API_PATH + "/question")
@Slf4j
public class QuestionController extends BaseController{
    @Autowired
    private QuestionService questionService;

    @GetMapping("/list")
    public ResponseEntity getQuestionsByConditions(QueryQuestionParam queryQuestionParam, HttpServletRequest request) {
        List<Question> questions = questionService.getQuestionsByConditions(queryQuestionParam);
        return ResponseEntity.ok(new ResultMap(tokenUtils).successAndRefreshToken(request).payload(questions));
    }
}

