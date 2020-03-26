package com.xupt.tmp.dto.paperDto;

import com.alibaba.fastjson.JSONObject;
import com.xupt.tmp.model.QuestionPaper;
import lombok.Data;

import java.util.List;

@Data
public class PaperResult {
    /**
     * 试卷Id
     */
    private long id;
    /**
     * 课程Id
     */
    private long courseId;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 创建人Id
     */
    private String createId;
    /**
     * 试卷名称
     */
    private String name;
    /**
     * 试题
     */
    private List<Long> questions;

    public PaperResult() {
    }

    public PaperResult(QuestionPaper questionPaper) {
        this.id = questionPaper.getId();
        this.createId = questionPaper.getCreateId();
        this.name = questionPaper.getName();
        this.courseId = questionPaper.getCourseId();
        this.courseName = questionPaper.getCourseName();
        this.questions = JSONObject.parseArray(questionPaper.getQuestions(), Long.class);
    }

}
