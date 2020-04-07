package com.xupt.tmp.dto.gradeDto;

import com.xupt.tmp.dto.questionDto.QuestionFResult;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class GradeResult {
    /**
     * 成绩id
     */
    private long id;
    /**
     * 学号
     */
    private String studentId;
    /**
     * 测试id
     */
    private long contestId;
    /**
     * 总成绩
     */
    private int result;
    /**
     * 自动阅卷成绩
     */
    private int autoResult;
    /**
     * 人工成绩
     */
    private int manulResult;
    /**
     * 回答记录
     */
    private String answerJson;
    /**
     * 提交时间
     */
    private Date createTime;
    /**
     * 阅卷时间
     */
    private Date finishTime;
    /**
     * 是否批阅
     * 0 - 未批阅
     * 1- 批阅
     */
    private int state;
    private List<QuestionFResult> questionResults;
}
