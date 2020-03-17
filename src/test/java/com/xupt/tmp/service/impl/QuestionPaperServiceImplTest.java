package com.xupt.tmp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xupt.tmp.dto.paperDto.QuestionPaperResult;
import com.xupt.tmp.model.QuestionPaper;
import com.xupt.tmp.service.QuestionPaperService;
import com.xupt.tmp.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class QuestionPaperServiceImplTest {

    @Autowired
    private QuestionPaperService questionPaperService;
    @Autowired
    private QuestionService questionService;


    @Test
    void getPaper() {
        List<QuestionPaperResult> paper = questionPaperService.getPaper("2006000894");
        System.out.println(paper);
    }

    @Test
    void addPaper() {
        for (int i = 2; i < 30; i++) {
            QuestionPaper questionPaper = new QuestionPaper();
            questionPaper.setId(i);
            questionPaper.setName("期中考试"+ i);
            questionPaper.setCreateId("2006000894");
            String smg = JSONObject.toJSONString(Arrays.asList(4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14));
            questionPaper.setQuestions(smg);
            questionPaper.setStatus(1);
            System.out.println(questionPaper.toString());
            questionPaperService.addPaper(questionPaper);
        }
    }
}

