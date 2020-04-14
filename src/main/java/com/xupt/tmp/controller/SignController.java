package com.xupt.tmp.controller;

import com.xupt.tmp.annotion.CurrentUser;
import com.xupt.tmp.common.Consts;
import com.xupt.tmp.dto.ResultMap;
import com.xupt.tmp.dto.signDto.SignCommit;
import com.xupt.tmp.dto.signDto.SignCreate;
import com.xupt.tmp.dto.signDto.SignTaskResult;
import com.xupt.tmp.model.SignRecord;
import com.xupt.tmp.model.User;
import com.xupt.tmp.service.SignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = Consts.BASE_API_PATH + "/sign")
public class SignController {

    @Autowired
    private SignService signService;

    @PutMapping
    public ResponseEntity sign(@RequestBody SignCommit signCommit, @CurrentUser User user) {
        ResultMap sign = signService.sign(signCommit.getId(), user.getUsername());
        return ResponseEntity.ok(sign);
    }

    @PostMapping
    public ResponseEntity createSign(@RequestBody SignCreate signCreate, HttpServletRequest request) {
        signService.createSign(signCreate, request);
        return ResponseEntity.ok(new ResultMap().success());
    }

    @GetMapping("/list")
    public ResponseEntity getSignTask(@CurrentUser User user) {
        List<SignTaskResult> signTasks = signService.getSignTasks(user.getUsername());
        return ResponseEntity.ok(new ResultMap().success().payloads(signTasks));
    }

    @GetMapping("/record")
    public ResponseEntity getSignRecords(Long id) {
        List<SignRecord> signRecord = signService.getSignRecord(id);
        return ResponseEntity.ok(new ResultMap().success().payloads(signRecord));
    }

    @GetMapping("/records")
    public ResponseEntity getSignRecordsByUserName(@CurrentUser User user) {
        List<SignTaskResult> signTasks = signService.getSignRecords(user.getUsername());
        return ResponseEntity.ok(new ResultMap().success().payloads(signTasks));
    }
}
