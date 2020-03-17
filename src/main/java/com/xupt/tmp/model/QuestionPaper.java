package com.xupt.tmp.model;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class QuestionPaper {
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
    private String questions;

    private int status;

    private DateTime createTime;
    private DateTime updateTime;

}
