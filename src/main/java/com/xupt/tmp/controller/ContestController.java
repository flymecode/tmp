package com.xupt.tmp.controller;

import com.alibaba.fastjson.JSONObject;
import com.xupt.tmp.common.Consts;
import com.xupt.tmp.dto.ResultMap;
import com.xupt.tmp.dto.contestDto.ContestCreate;
import com.xupt.tmp.model.Contest;
import com.xupt.tmp.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = Consts.BASE_API_PATH + "/contest")
public class ContestController {

    @Autowired
    private ContestService contestService;

    @PostMapping
    public ResponseEntity createContest(@Valid @RequestBody ContestCreate contestCreate,
                                        @ApiIgnore BindingResult bindingResult) throws ParseException {
        if (bindingResult.hasErrors()) {
            ResultMap resultMap = new ResultMap().fail().message(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.status(resultMap.getCode()).body(resultMap);
        }
        contestService.createContest(contestCreate);
        return ResponseEntity.ok(new ResultMap().success());
    }

    @PostMapping("/update")
    public ResponseEntity updateContest(@RequestBody Contest contest) {
        boolean result = contestService.updateContest(contest);
        return ResponseEntity.ok(new ResultMap().success());
    }

    @DeleteMapping
    public ResponseEntity deleteContest(@PathVariable int id) {
        contestService.deleteContest(id);
        return ResponseEntity.ok(new ResultMap().success());
    }

    @GetMapping
    public ResponseEntity getContest(@RequestParam("ids") String ids) {
        List<Long> courseId   = JSONObject.parseArray(ids, Long.class);
        List<Contest> list = contestService.getContestsByCourseId(courseId);
        return ResponseEntity.ok(new ResultMap().success().payloads(list));
    }



    /**w
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
