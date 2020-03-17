package com.xupt.tmp.dto.paperDto;

import com.xupt.tmp.model.Question;
import com.xupt.tmp.model.QuestionPaper;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.List;

@Data
public class QuestionPaperResult {
    /**
     * 试卷ID
     */
    private long id;
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
    private List<Question> list;

    private DateTime createTime;
    private DateTime updateTime;

    public QuestionPaperResult(){}

    public QuestionPaperResult(QuestionPaper questionPaper, List<Question> list) {
        this.id = questionPaper.getId();
        this.createId = questionPaper.getCreateId();
        this.name = questionPaper.getName();
        this.list = list;
    }
}
