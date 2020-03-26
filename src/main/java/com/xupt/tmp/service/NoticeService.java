package com.xupt.tmp.service;

import com.xupt.tmp.dto.noticeDto.CreateNotice;
import com.xupt.tmp.model.Notice;

public interface NoticeService {

    Notice addNotice(CreateNotice createNotice);
}
