package com.xupt.tmp.dto.noticeDto;

import lombok.Data;

@Data
public class NoticeCreate {
    /**
     * 标题
     */
    private String name;
    /**
     *
     */
    private Long[] value;
    /**
     * 文本内容
     */
    private String content;
    /**
     * 权重
     */
    private int weight;
}
