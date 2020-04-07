package com.xupt.tmp.controller;

import com.alibaba.fastjson.JSONObject;
import com.xupt.tmp.annotion.CurrentUser;
import com.xupt.tmp.common.Consts;
import com.xupt.tmp.dto.ResultMap;
import com.xupt.tmp.dto.contestDto.ContestAnswer;
import com.xupt.tmp.dto.contestDto.ContestCreate;
import com.xupt.tmp.dto.contestDto.ContestResult;
import com.xupt.tmp.model.Contest;
import com.xupt.tmp.model.Grade;
import com.xupt.tmp.model.User;
import com.xupt.tmp.service.ContestService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = Consts.BASE_API_PATH + "/contest")
@Slf4j
public class ContestController {

    @Autowired
    private ContestService contestService;

    @PostMapping
    public ResponseEntity createContest(@Valid @RequestBody ContestCreate contestCreate,
                                        @ApiIgnore BindingResult bindingResult,HttpServletRequest request) throws ParseException {
        if (bindingResult.hasErrors()) {
            ResultMap resultMap = new ResultMap().fail().message(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.status(resultMap.getCode()).body(resultMap);
        }
        contestService.createContest(contestCreate,request);
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

    /**
     * 根据课程id获取测试
     *
     * @param ids
     * @return
     */
    @GetMapping
    public ResponseEntity getContest(@RequestParam("ids") String ids) {
        List<Long> courseId = JSONObject.parseArray(ids, Long.class);
        List<ContestResult> list = contestService.getContestsByCourseId(courseId);
        return ResponseEntity.ok(new ResultMap().success().payloads(list));
    }

    @PutMapping
    public ResponseEntity updateContestState(@RequestParam("id") Long id, int state) {
        contestService.updateContestState(id, state);
        return ResponseEntity.ok(new ResultMap().success());
    }

    @PostMapping("/commit")
    public ResponseEntity commitContest(@RequestBody ContestAnswer contestAnswer, @CurrentUser User user) {
        contestService.commitContest(contestAnswer, user.getUsername());
        return ResponseEntity.ok(new ResultMap().success());
    }

    @GetMapping("/record")
    public ResponseEntity getContestRecord(@Param("id") Long id, @CurrentUser User user) {
        boolean result = contestService.getContestRecode(id, user.getUsername());
        return ResponseEntity.ok(new ResultMap().success().payload(result));
    }

    @GetMapping("/record/list")
    public ResponseEntity getContestRecord(@Param("id") Long id) {
        List<Grade> list = contestService.getContestRecords(id);
        return ResponseEntity.ok(new ResultMap().success().payload(list));
    }

    /**
     * 根据老师id查询
     *
     * @param request
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity getContestByCreateId(HttpServletRequest request) {
        List<ContestResult> list = contestService.getContestByCreateId(request);
        return ResponseEntity.ok(new ResultMap().success().payload(list));
    }
}
