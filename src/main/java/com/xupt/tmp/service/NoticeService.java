package com.xupt.tmp.service;

import com.github.pagehelper.PageInfo;
import com.xupt.tmp.dto.noticeDto.NoticeCreate;
import com.xupt.tmp.dto.noticeDto.NoticeQuery;
import com.xupt.tmp.dto.noticeDto.NoticeUpdate;
import com.xupt.tmp.model.Notice;
import com.xupt.tmp.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface NoticeService {

    void addNotice(NoticeCreate createNotice, HttpServletRequest request);

    void updateNotice(NoticeUpdate noticeUpdate);

    List<Notice> getNoticeByCourseIds(List<Long> courseIds);

    List<Notice> getNotices(User user);

    PageInfo<Notice> getNotices(NoticeQuery query, HttpServletRequest request);

    void deleteNotice(Long id);
}
