package com.xupt.tmp.service.impl;

import com.xupt.tmp.dto.ResultMap;
import com.xupt.tmp.mapper.QuestionMapper;
import com.xupt.tmp.model.Question;
import com.xupt.tmp.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public ResultMap getQuestionsByType(int type) {
        ResultMap result = new ResultMap();
        List<Question>  questions = questionMapper.getQuestionByType(type);
        result.success().payloads(questions);
        return result;
    }

    @Override
    public ResultMap updateQuestionById(long id) {
        return null;
    }

    @Override
    public ResultMap deleteQuestionById(long id) {
        return null;
    }
}
