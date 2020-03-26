package com.xupt.tmp.model;

import lombok.Data;

import java.util.Date;

/**
 * 通知消息
 */
@Data
public class Notice {
    /**
     * 通知id
     */
    private long id;
    /**
     * 文本内容
     */
    private String content;
    /**
     * 操作人id
     */
    private String operateId;
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

    private Date updateTime;
    private Date createTime;
    private Date deleteTime;
}
