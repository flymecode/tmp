package com.xupt.tmp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xupt.tmp.config.PageUtils;
import com.xupt.tmp.dto.ResultMap;
import com.xupt.tmp.dto.questionDto.QuestionQueryParam;
import com.xupt.tmp.dto.questionDto.QuestionCreate;
import com.xupt.tmp.dto.questionDto.QuestionResult;
import com.xupt.tmp.exception.ServerException;
import com.xupt.tmp.mapper.QuestionMapper;
import com.xupt.tmp.model.Question;
import com.xupt.tmp.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public PageInfo<Question> getQuestionsByConditions(QuestionQueryParam questionQueryParam) {
        int pageNum = questionQueryParam.getPage();
        int pageSize = questionQueryParam.getLimit();
        String sort = questionQueryParam.getSort();
        if (!PageUtils.checkPageInfo(pageNum, pageSize)) {
            throw new ServerException("Invalid page info");
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Question> questions = questionMapper.getQuestionsByConditions(questionQueryParam);
        if (StringUtils.isNotEmpty(sort)) {
            if (sort.equals("+id")) {
                questions.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
            } else {
                questions.sort((o1, o2) -> (int) (o2.getId() - o1.getId()));
            }
        }
        PageInfo<Question> questionPageInfo = new PageInfo<>(questions);
        return questionPageInfo;
    }

    @Override
    public ResultMap updateQuestionById(long id) {
        return null;
    }

    @Override
    public ResultMap deleteQuestionById(long id) {
        questionMapper.deleteQuestionById(id);
        return new ResultMap().success();
    }

    @Override
    public List<QuestionResult> getQuestionsById(List<Long> list) {

        List<Question> questions = questionMapper.selectQuestionByIds(list);
        List<QuestionResult> questionResults = new ArrayList<>();
        for (Question question : questions) {
            QuestionResult questionResult = new QuestionResult();
            BeanUtils.copyProperties(question, questionResult);
            questionResults.add(questionResult);
        }
        return questionResults;
    }

    @Override
    public void addQuestion(QuestionCreate questionCreate) {
        Question question = new Question();
        BeanUtils.copyProperties(questionCreate, question);
        question.setContent(questionCreate.getTitle());
        question.setTag("");
        question.setParse("");
        question.setCreateTime(new Date());
        question.setUpdateTime(new Date());
        questionMapper.insert(question);
    }
}
