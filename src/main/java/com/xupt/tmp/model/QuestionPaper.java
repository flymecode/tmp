package com.xupt.tmp.model;

import lombok.Data;

@Data
public class QuestionPaper {
    private long id;
    private long contestId;
    private String questions;
}
