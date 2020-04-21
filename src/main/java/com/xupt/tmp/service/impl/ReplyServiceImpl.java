package com.xupt.tmp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xupt.tmp.common.Consts;
import com.xupt.tmp.dto.replyDto.ReplyAgree;
import com.xupt.tmp.dto.replyDto.ReplyCreate;
import com.xupt.tmp.dto.replyDto.ReplyNoAgree;
import com.xupt.tmp.dto.replyDto.ReplyQuery;
import com.xupt.tmp.mapper.CourseMapper;
import com.xupt.tmp.mapper.ReplyMapper;
import com.xupt.tmp.mapper.SignMapper;
import com.xupt.tmp.model.Course;
import com.xupt.tmp.model.Reply;
import com.xupt.tmp.model.User;
import com.xupt.tmp.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private SignMapper signMapper;

    @Override
    public PageInfo<Reply> getReplys(String username, ReplyQuery query) {
        PageHelper.startPage(query.getPage(), query.getLimit());
        List<Reply> replies = replyMapper.selectReply(username);
        PageInfo<Reply> pageInfo = new PageInfo<>(replies);
        return pageInfo;
    }

    @Override
    public void createReply(ReplyCreate createReply, HttpServletRequest request) {
        Reply reply = new Reply();
        int type = createReply.getType();
        User user = (User) request.getAttribute(Consts.CURRENT_USER);
        reply.setAgree(false);
        reply.setCourseId(createReply.getCourseId());
        reply.setClazzId(createReply.getClazzId());
        Course course = courseMapper.selectCourseById(createReply.getCourseId());
        reply.setTeacherId(course.getCreateId());
        reply.setCreateTime(new Date());
        reply.setUpdateTime(new Date());
        reply.setSignId(createReply.getSignId());
        reply.setName(user.getName());
        reply.setUsername(user.getUsername());
        reply.setReason(createReply.getReason());
        reply.setType(type);
        if (type != 1) {
            Date[] date = createReply.getDate();
            reply.setStartTime(date[0]);
            reply.setEndTime(date[1]);
        }
        replyMapper.insert(reply);
    }

    @Override
    public void updateReply(Reply reply) {

    }

    @Override
    public void noAgreeReply(ReplyNoAgree replyAgree) {
        replyMapper.updateReply(replyAgree);
    }

    @Override
    @Transactional
    public void agreeReply(ReplyAgree replyAgree) {
        long id = replyAgree.getId();
        long signId = replyAgree.getSignId();
        String uesrname = replyAgree.getUsername();
        signMapper.updateSignRecord(uesrname, signId, new Date());
        replyMapper.updateReplyAgree(id);
    }

}
