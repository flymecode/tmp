package com.xupt.tmp.controller;

import com.xupt.tmp.annotion.CurrentUser;
import com.xupt.tmp.common.Consts;
import com.xupt.tmp.dto.ResultMap;
import com.xupt.tmp.dto.replyDto.CreateReply;
import com.xupt.tmp.dto.replyDto.ReplyQuery;
import com.xupt.tmp.model.Reply;
import com.xupt.tmp.model.User;
import com.xupt.tmp.service.ReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = Consts.BASE_API_PATH + "/reply")
@Slf4j
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @PostMapping("/list")
    public ResponseEntity getReplys(@CurrentUser User user, ReplyQuery query) {
        List<Reply> replies = replyService.getReplys(user.getUsername(), query);
        return ResponseEntity.ok(new ResultMap().success().payload(replies));
    }

    @PostMapping
    public ResponseEntity createReply(CreateReply createReply, HttpServletRequest request) {
        replyService.createReply(createReply, request);
        return ResponseEntity.ok(new ResultMap().success());
    }

    @PutMapping
    public ResponseEntity updateReply(Reply reply) {
        replyService.updateReply(reply);
        return ResponseEntity.ok(new ResultMap().success());
    }
}
