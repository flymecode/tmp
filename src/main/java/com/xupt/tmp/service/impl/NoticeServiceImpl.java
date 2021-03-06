package com.xupt.tmp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xupt.tmp.common.Consts;
import com.xupt.tmp.dto.noticeDto.NoticeCreate;
import com.xupt.tmp.dto.noticeDto.NoticeQuery;
import com.xupt.tmp.dto.noticeDto.NoticeUpdate;
import com.xupt.tmp.mapper.NoticeMapper;
import com.xupt.tmp.mapper.UCCMapper;
import com.xupt.tmp.model.Notice;
import com.xupt.tmp.model.UCCRelation;
import com.xupt.tmp.model.User;
import com.xupt.tmp.service.NoticeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    private UCCMapper uccMapper;

    @Override
    public void addNotice(NoticeCreate createNotice, HttpServletRequest request) {
        Notice notice = new Notice();
        User user = (User) request.getAttribute(Consts.CURRENT_USER);
        Long[] value = createNotice.getValue();
        notice.setCreateId(user.getUsername());
        notice.setCourseId(value[0]);
        notice.setClazzId(value[1]);
        notice.setContent(createNotice.getContent());
        notice.setName(createNotice.getName());
        notice.setCreateTime(new Date());
        notice.setUpdateTime(new Date());
        notice.setStatus(0);
        notice.setWeight(createNotice.getWeight());
        noticeMapper.inster(notice);
    }


    @Override
    public void updateNotice(NoticeUpdate noticeUpdate) {
        Notice notice = new Notice();
        BeanUtils.copyProperties(noticeUpdate, notice);
        noticeMapper.updateNotice(notice);
    }

    @Override
    public List<Notice> getNoticeByCourseIds(List<Long> courseIds) {
        List<Notice> notices = noticeMapper.getNoticeByCourseIds(courseIds);
        return notices;
    }

    @Override
    public List<Notice> getNotices(User user) {
        List<Notice> result = new ArrayList<>();
        List<UCCRelation> uccRelations = uccMapper.selectRelation(user.getUsername());
        if (uccRelations != null) {
            for (UCCRelation uccRelation : uccRelations) {
                List<Notice> list = noticeMapper.selectNoticesByCourseIdAndClazzId(uccRelation.getCourseId(), uccRelation.getClazzId());
                if (list != null) {
                    result.addAll(list);
                }
            }
        }
        return result;
    }

    @Override
    public PageInfo<Notice> getNotices(NoticeQuery query, HttpServletRequest request) {
        PageHelper.startPage(query.getPage(), query.getLimit());
        User user = (User) request.getAttribute(Consts.CURRENT_USER);
        query.setUsername(user.getUsername());
        List<Notice> notices = noticeMapper.selectNotices(query);
        PageInfo<Notice> pageInfo = new PageInfo<>(notices);
        return pageInfo;
    }

    @Override
    public void deleteNotice(Long id) {
        noticeMapper.delete(id);
    }

}
