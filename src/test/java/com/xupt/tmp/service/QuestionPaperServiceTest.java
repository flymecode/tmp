package com.xupt.tmp.service;

import com.alibaba.fastjson.JSONObject;
import com.xupt.tmp.dto.paperDto.QuestionPaperResult;
import com.xupt.tmp.model.QuestionPaper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class QuestionPaperServiceTest {
    @Autowired
    private QuestionPaperService questionPaperService;
    @Autowired
    private QuestionService questionService;

    @Test
    void getPaperAndQuestion() {
        List<QuestionPaperResult> paper = questionPaperService.getPaperAndQuestion("2006000894");
        System.out.println(paper);
    }

    @Test
    void addPaper() {
        for (int i = 1; i < 30; i++) {
            QuestionPaper questionPaper = new QuestionPaper();
            questionPaper.setId(i);
            questionPaper.setName("期中考试"+ i);
            questionPaper.setCreateId("2006000894");
            String smg = JSONObject.toJSONString(Arrays.asList(4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14));
            questionPaper.setQuestions(smg);
            questionPaper.setStatus(1);
            questionPaper.setCourseId(i % 5);
            questionPaper.setCourseName("数据结构"+i % 5);
            System.out.println(questionPaper.toString());
            questionPaperService.addPaper(questionPaper);
        }
    }

    @Test
    void getPaperInfo() {

    }

    @Test
    void deletePaper() {
        questionPaperService.deletePaper();
    }
}
