package com.xupt.tmp.controller;

import com.xupt.tmp.annotion.CurrentUser;
import com.xupt.tmp.common.Consts;
import com.xupt.tmp.dto.ResultMap;
import com.xupt.tmp.dto.paperDto.PaperResult;
import com.xupt.tmp.model.QuestionPaper;
import com.xupt.tmp.model.User;
import com.xupt.tmp.service.QuestionPaperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping(value = Consts.BASE_API_PATH + "/paper")
public class QuestionPaperController extends BaseController {

    @Autowired
    private QuestionPaperService questionPaperService;

    @GetMapping
    public ResponseEntity getPaper(@CurrentUser User user, int page, int limit) {
        List<QuestionPaper> list = questionPaperService.getPaperInfo(user.getUsername(), page, limit);
        List<PaperResult> collect = list.stream().map(PaperResult::new).collect(Collectors.toList());
        return ResponseEntity.ok(new ResultMap().success().payloads(collect));
    }

    @GetMapping("/list")
    public ResponseEntity getPapers(String name, HttpServletRequest request) {
        List<QuestionPaper> list = questionPaperService.getPaperName(name, request);
        return ResponseEntity.ok(new ResultMap().success().payloads(list));
    }
}
