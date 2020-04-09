package com.xupt.tmp.service;

import com.xupt.tmp.dto.replyDto.CreateReply;
import com.xupt.tmp.dto.replyDto.ReplyQuery;
import com.xupt.tmp.model.Reply;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ReplyService {

    List<Reply> getReplys(String username, ReplyQuery query);

    void createReply(CreateReply reply, HttpServletRequest request);

    void updateReply(Reply reply);
}
