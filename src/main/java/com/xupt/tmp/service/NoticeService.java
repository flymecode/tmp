package com.xupt.tmp.service;

import com.xupt.tmp.dto.noticeDto.CreateNotice;
import com.xupt.tmp.dto.noticeDto.NoticeUpdate;
import com.xupt.tmp.model.Notice;
import com.xupt.tmp.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface NoticeService {

    void addNotice(CreateNotice createNotice, HttpServletRequest request);

    List<Notice> getNotices(int page, int limit, HttpServletRequest request);

    void updateNotice(NoticeUpdate noticeUpdate);

    List<Notice> getNoticeByCourseIds(List<Long> courseIds);

    List<Notice> getNotices(User user);
}
