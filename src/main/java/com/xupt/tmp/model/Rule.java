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
}
