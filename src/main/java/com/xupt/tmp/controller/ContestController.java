package com.xupt.tmp.controller;

import com.xupt.tmp.common.Consts;
import com.xupt.tmp.dto.ResultMap;
import com.xupt.tmp.dto.contestDto.ContestCreate;
import com.xupt.tmp.model.Contest;
import com.xupt.tmp.service.ContestService;
import com.xupt.tmp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping(value = Consts.BASE_API_PATH + "/contest")
public class ContestController {

    @Autowired
    private ContestService contestService;
    @Autowired
    private QuestionService questionService;

    @PostMapping
    public ResponseEntity addContest(@Valid @RequestBody ContestCreate contestCreate, @ApiIgnore BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ResultMap resultMap = new ResultMap().fail().message(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.status(resultMap.getCode()).body(resultMap);
        }
        contestService.addContest(contestCreate);
        return ResponseEntity.ok(new ResultMap().success());
    }

    @PostMapping("/update")
    public ResponseEntity updateContest(@RequestBody Contest contest) {
        boolean result = contestService.updateContest(contest);
        return ResponseEntity.ok(new ResultMap().success());
    }

    @DeleteMapping
    public ResponseEntity deleteContest(@PathVariable int id) {
        boolean result = contestService.deleteContest(id);
        return ResponseEntity.ok(new ResultMap().success());
    }

    /**
     * 完成考试批改
     */
//    @PostMapping
//    public ResponseEntity finishContest(@PathVariable int id) {
//        Contest contest = contestService.getContestById(id);
//        contest.setState(3);
//        questionService.updateQuestionsStateByContestId(id, 1);
//        boolean result = contestService.updateContest(contest);
//    }


}
