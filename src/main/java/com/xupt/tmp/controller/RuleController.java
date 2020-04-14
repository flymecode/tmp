package com.xupt.tmp.controller;

import com.xupt.tmp.annotion.CurrentUser;
import com.xupt.tmp.common.Consts;
import com.xupt.tmp.dto.ResultMap;
import com.xupt.tmp.model.Rule;
import com.xupt.tmp.model.User;
import com.xupt.tmp.service.RuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = Consts.BASE_API_PATH + "/rule")
public class RuleController {
    @Autowired
    private RuleService ruleService;

    @GetMapping
    public ResponseEntity getRules(@CurrentUser User user) {
        List<Rule> ruleList = ruleService.getRules(user.getUsername());
        return ResponseEntity.ok(new ResultMap().success().payload(ruleList));
    }

    @PostMapping
    public ResponseEntity createRule(@RequestBody Rule rule, @CurrentUser User user) {
        ruleService.addRule(rule, user);
        return ResponseEntity.ok(new ResultMap().success());
    }
}
