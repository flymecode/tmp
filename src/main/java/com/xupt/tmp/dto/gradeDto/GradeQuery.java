package com.xupt.tmp.dto.gradeDto;

import lombok.Data;

@Data
public class GradeQuery {
    private int page;
    private int limit;
    /**
     * 课程 and 班级
     */
    private long[] value;

    private int ruleId;
}
