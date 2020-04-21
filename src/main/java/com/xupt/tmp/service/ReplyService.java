package com.xupt.tmp.service;

import com.github.pagehelper.PageInfo;
import com.xupt.tmp.dto.replyDto.ReplyAgree;
import com.xupt.tmp.dto.replyDto.ReplyCreate;
import com.xupt.tmp.dto.replyDto.ReplyNoAgree;
import com.xupt.tmp.dto.replyDto.ReplyQuery;
import com.xupt.tmp.model.Reply;

import javax.servlet.http.HttpServletRequest;

public interface ReplyService {

    PageInfo<Reply> getReplys(String username, ReplyQuery query);

    void createReply(ReplyCreate reply, HttpServletRequest request);

    void agreeReply(ReplyAgree replyAgree);

    void updateReply(Reply reply);

    void noAgreeReply(ReplyNoAgree replyAgree);
}
