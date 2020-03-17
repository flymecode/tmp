package com.xupt.tmp.service;

import com.xupt.tmp.dto.paperDto.QuestionPaperResult;
import com.xupt.tmp.model.QuestionPaper;

import java.util.List;

public interface QuestionPaperService {

    List<QuestionPaperResult> getPaper(String username);

    QuestionPaperResult addPaper(QuestionPaper questionPaper);
}
