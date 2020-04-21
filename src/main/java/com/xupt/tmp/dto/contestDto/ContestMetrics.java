package com.xupt.tmp.dto.contestDto;

import lombok.Data;

import java.util.Map;

@Data
public class ContestMetrics {
    /**
     * 平均分
     */
    private long averageScore;
    /**
     * 指标
     */
    private Map<String, Integer> metrics;
}
