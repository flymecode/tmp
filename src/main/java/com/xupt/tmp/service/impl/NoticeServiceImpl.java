package com.xupt.tmp.service.impl;

import com.xupt.tmp.dto.noticeDto.CreateNotice;
import com.xupt.tmp.mapper.NoticeMapper;
import com.xupt.tmp.model.Notice;
import com.xupt.tmp.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    @Override
    public Notice addNotice(CreateNotice createNotice) {
        Notice notice = new Notice();
        noticeMapper.inster(notice);
        return null;
    }

}
