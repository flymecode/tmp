package com.xupt.tmp.service;

import com.xupt.tmp.dto.paperDto.QuestionPaperResult;
import com.xupt.tmp.model.QuestionPaper;

import java.util.List;

public interface QuestionPaperService {


    /**
     * 获取试卷和问题信息
     * @param username
     * @return
     */
    List<QuestionPaperResult> getPaperAndQuestion(String username);

    /**
     * 添加试卷
     * @param questionPaper
     * @return
     */
    QuestionPaperResult addPaper(QuestionPaper questionPaper);

    /**
     * 获取试卷信息
     * @param username
     * @param page
     * @param limit
     * @return
     */
    List<QuestionPaper> getPaperInfo(String username, int page, int limit);

    void deletePaper();

}
