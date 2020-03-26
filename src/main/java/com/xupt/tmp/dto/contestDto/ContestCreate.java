package com.xupt.tmp.dto.contestDto;

import lombok.Data;

import java.util.List;

@Data
public class ContestCreate {
    /**
     * 试卷ID
     */
    private long id;
    /**
     * 测试名称
     */
    private String title;
    /**
     * 试卷名称
     */
    private String name;
    /**
     * 课程ID
     */
    private long courseId;
    /**
     * 创建人
     */
    private String createId;

    /**
     * 试题
     */
    private List<Long> questions;
    /**
     * 测试类型
     */
    private int type;

    /**
     * 时间
     */
    private List<String> time;

}
