package com.xupt.tmp.dto.gradeDto;

import lombok.Data;

import java.util.Date;

@Data
public class GradeCreate {
    /**
     * 学号
     */
    private String studentId;
    /**
     * 测试id
     */
    private long contestId;
    /**
     * 回答记录
     */
    private String answerJson;
    /**
     * 提交时间
     */
    private Date createTime;
}
