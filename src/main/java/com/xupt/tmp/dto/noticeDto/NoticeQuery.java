package com.xupt.tmp.dto.noticeDto;

import com.xupt.tmp.dto.BasePageInfo;
import lombok.Data;

@Data
public class NoticeQuery extends BasePageInfo {
    private long clazzId;
    private long courseId;
    private String username;
}
