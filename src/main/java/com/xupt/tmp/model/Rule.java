package com.xupt.tmp.model;

import lombok.Data;

@Data
public class Rule {
    /**
     * 测试比重
     */
    private int contestWeight;
    /**
     * 签到比重
     */
    private int signWeight;

    /**
     * 平时作业比重
     */
    private int homeworkWeight;

    /**
     * 创建人
     */
    private String username;
}
