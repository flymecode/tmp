package com.xupt.tmp.controller;

import com.xupt.tmp.common.Consts;
import com.xupt.tmp.dto.ResultMap;
import com.xupt.tmp.dto.signDto.SignCreate;
import com.xupt.tmp.model.SignRecord;
import com.xupt.tmp.service.SignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = Consts.BASE_API_PATH + "/sign")
public class SignController {

    @Autowired
    private SignService signService;

    @PutMapping
    public ResponseEntity sign(String username) {
        signService.sign(username);
        return ResponseEntity.ok(new ResultMap().success());
    }

    @PostMapping("/create")
    public ResponseEntity createSign(SignCreate signCreate) {
        signService.createSign(signCreate);
        return ResponseEntity.ok(new ResultMap().success());
    }

    @GetMapping
    public ResponseEntity getSigns(String username) {
        List<SignRecord> signRecords = signService.getSignRecord(username);
        return ResponseEntity.ok(new ResultMap().success().payloads(signRecords));
    }
}
