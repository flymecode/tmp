package com.xupt.tmp.controller;

import com.github.pagehelper.PageInfo;
import com.xupt.tmp.annotion.CurrentUser;
import com.xupt.tmp.common.Consts;
import com.xupt.tmp.dto.ResultMap;
import com.xupt.tmp.dto.replyDto.ReplyAgree;
import com.xupt.tmp.dto.replyDto.ReplyCreate;
import com.xupt.tmp.dto.replyDto.ReplyNoAgree;
import com.xupt.tmp.dto.replyDto.ReplyQuery;
import com.xupt.tmp.model.Reply;
import com.xupt.tmp.model.User;
import com.xupt.tmp.service.ReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = Consts.BASE_API_PATH + "/reply")
@Slf4j
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @PostMapping("/list")
    public ResponseEntity getReplys(@CurrentUser User user, @RequestBody ReplyQuery query) {
        PageInfo<Reply> replies = replyService.getReplys(user.getUsername(), query);
        return ResponseEntity.ok(new ResultMap().success().payload(replies));
    }

    @PostMapping
    public ResponseEntity createReply(@RequestBody ReplyCreate createReply, HttpServletRequest request) {
        replyService.createReply(createReply, request);
        return ResponseEntity.ok(new ResultMap().success());
    }

    /**
     * 学生更新申请
     *
     * @param reply
     * @return
     */
    @PutMapping
    public ResponseEntity updateReply(Reply reply) {
        replyService.updateReply(reply);
        return ResponseEntity.ok(new ResultMap().success());
    }

    @PutMapping("/agree")
    public ResponseEntity agree(@RequestBody ReplyAgree replyAgree) {
        replyService.agreeReply(replyAgree);
        return ResponseEntity.ok(new ResultMap().success());
    }

    @PutMapping("/no")
    public ResponseEntity noAgree(@RequestBody ReplyNoAgree replyNoAgree) {
        replyService.noAgreeReply(replyNoAgree);
        return ResponseEntity.ok(new ResultMap().success());
    }
}
