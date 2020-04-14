package com.xupt.tmp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.xupt.tmp.common.Consts;
import com.xupt.tmp.dto.paperDto.QuestionPaperResult;
import com.xupt.tmp.mapper.QuestionMapper;
import com.xupt.tmp.mapper.QuestionPaperMapper;
import com.xupt.tmp.model.Question;
import com.xupt.tmp.model.QuestionPaper;
import com.xupt.tmp.model.User;
import com.xupt.tmp.service.QuestionPaperService;
import io.jsonwebtoken.lang.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
    public List<QuestionPaperResult> getPaperAndQuestion(String username) {

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
        String questions = questionPaper.getQuestions();
        List<Long> questionIds = JSONObject.parseArray(questions, Long.class);
        List<Question> questionList = questionMapper.selectQuestionByIds(questionIds);
        if (questionList != null) {
            int score = 0;
            for (Question question : questionList) {
                score += question.getScore();
            }
            questionPaper.setPaperScore(score);
        }
        questionPaperMapper.insert(questionPaper);
        return new QuestionPaperResult();
    }

    @Override
    public List<QuestionPaper> getPaperInfo(String username, int page, int limit) {
        PageHelper.startPage(page, limit);
        List<QuestionPaper> questionPapers = questionPaperMapper.selectPaperByCreateId(username);
        if (Collections.isEmpty(questionPapers)) {
            return new ArrayList<>();
        }
        return questionPapers;
    }

    @Override
    public void deletePaper() {
        questionPaperMapper.deletePaper();
    }

    @Override
    public List<QuestionPaper> getPaperName(String name, HttpServletRequest request) {
        User user = (User) request.getAttribute(Consts.CURRENT_USER);
        List<QuestionPaper> result = questionPaperMapper.selectPaperName(name, user.getUsername());
        return result;
    }


}
