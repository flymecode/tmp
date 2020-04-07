package com.xupt.tmp.dto.contestDto;

import lombok.Data;

@Data
public class ContestCreate {
    /**
     * 测试名称
     */
    private String title;
    /**
     * 课程ID and 班级ID
     */
    private Long[] value;
    /**
     * 试卷Id
     */
    private long questionPaperId;
    /**
     * 测试类型
     */
    private int type;

    /**
     * 时间
     */
    private Long[] time;

}
