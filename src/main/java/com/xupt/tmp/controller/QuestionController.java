package com.xupt.tmp.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.xupt.tmp.common.Consts;
import com.xupt.tmp.dto.ResultMap;
import com.xupt.tmp.dto.questionDto.QuestionContest;
import com.xupt.tmp.dto.questionDto.QuestionCreate;
import com.xupt.tmp.dto.questionDto.QuestionQueryParam;
import com.xupt.tmp.dto.questionDto.QuestionResult;
import com.xupt.tmp.model.Question;
import com.xupt.tmp.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.copyProperties;

@RestController
@RequestMapping(value = Consts.BASE_API_PATH + "/question")
@Slf4j
public class QuestionController extends BaseController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/list")
    public ResponseEntity getQuestionsByConditions(QuestionQueryParam questionQueryParam, HttpServletRequest request) {
        PageInfo<Question> questions = questionService.getQuestionsByConditions(questionQueryParam);
        return ResponseEntity.ok(new ResultMap(tokenUtils).successAndRefreshToken(request).payload(questions));
    }

    @GetMapping
    public ResponseEntity getQuestions(@RequestParam(value = "ids") String ids, HttpServletRequest request) {
        List<QuestionResult> questions = questionService.getQuestionsById(JSONObject.parseArray(ids, Long.class));
        return ResponseEntity.ok(new ResultMap(tokenUtils).successAndRefreshToken(request).payload(questions));
    }

    @GetMapping("/test")
    public ResponseEntity getQuestionsNoAnswer(@RequestParam(value = "ids") String ids, HttpServletRequest request) {
        List<Long> longs = JSONObject.parseArray(ids, Long.class);
        List<QuestionContest> list = questionService.getQuestionsById(longs).stream().map(e -> {
            QuestionContest questionContest = new QuestionContest();
            copyProperties(e, questionContest);
            return questionContest;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(new ResultMap(tokenUtils).success().payloads(list));
    }

    @PostMapping
    public ResponseEntity addQuestion(@RequestBody QuestionCreate questionCreate) {
        questionService.addQuestion(questionCreate);
        return ResponseEntity.ok(new ResultMap().success());
    }


}

