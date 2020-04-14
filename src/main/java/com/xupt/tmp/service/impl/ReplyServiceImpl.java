package com.xupt.tmp.service.impl;

import com.github.pagehelper.PageHelper;
import com.xupt.tmp.common.Consts;
import com.xupt.tmp.dto.replyDto.CreateReply;
import com.xupt.tmp.dto.replyDto.ReplyQuery;
import com.xupt.tmp.mapper.CourseMapper;
import com.xupt.tmp.mapper.ReplyMapper;
import com.xupt.tmp.model.Course;
import com.xupt.tmp.model.Reply;
import com.xupt.tmp.model.User;
import com.xupt.tmp.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Reply> getReplys(String username, ReplyQuery query) {
        PageHelper.startPage(query.getPage(), query.getLimit());
        return replyMapper.selectReply(username);
    }

    @Override
    public void createReply(CreateReply createReply, HttpServletRequest request) {
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
        replyMapper.updateReply(reply);
    }
}
