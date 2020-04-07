package com.xupt.tmp.dto.noticeDto;

import lombok.Data;
@Data
public class NoticeUpdate {
    /**
     * 通知id
     */
    private long id;
    /**
     * 课程Id
     */
    private long courseId;
    /**
     * 文本内容
     */
    private String content;
    /**
     * 状态
     * 0 - 展示
     * 1 - 下架
     */
    private int status;
    /**
     * 权重
     */
    private int weight;
}
