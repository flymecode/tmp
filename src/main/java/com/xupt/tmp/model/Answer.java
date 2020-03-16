package com.xupt.tmp.model;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class Answer {

    private long sid;
    private String body;
    private long score;

    private DateTime end;
    private DateTime create;
    private DateTime update;
}
