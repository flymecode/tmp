package com.xupt.tmp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xupt.tmp.dto.paperDto.QuestionPaperResult;
import com.xupt.tmp.mapper.QuestionMapper;
import com.xupt.tmp.mapper.QuestionPaperMapper;
import com.xupt.tmp.model.Question;
import com.xupt.tmp.model.QuestionPaper;
import com.xupt.tmp.service.QuestionPaperService;
import io.jsonwebtoken.lang.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class QuestionPaperServiceImpl implements QuestionPaperService {


    @Autowired
    private QuestionPaperMapper questionPaperMapper;
    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public List<QuestionPaperResult> getPaper(String username) {

        List<QuestionPaperResult> results = new ArrayList<>();
        List<QuestionPaper> questionPapers = questionPaperMapper.selectPaperByCreateId(username);
        if (!Collections.isEmpty(questionPapers)) {
            questionPapers.stream().forEach(questionPaper -> {
                List<Long> questionIds = JSONObject.parseArray(questionPaper.getQuestions(), Long.class);
                List<Question> questionList = questionMapper.selectQuestionByIds(questionIds);
                results.add(new QuestionPaperResult(questionPaper, questionList));
            });
        }
        return results;
    }

    @Override
    public QuestionPaperResult addPaper(QuestionPaper questionPaper) {
        questionPaperMapper.insert(questionPaper);
        return new QuestionPaperResult();
    }


}
