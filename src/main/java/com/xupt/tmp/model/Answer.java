package com.xupt.tmp.model;

import lombok.Data;
import org.joda.time.DateTime;

import java.util.Date;

@Data
public class Answer {

    private long sid;
    private String body;
    private long score;

    private DateTime end;
    private Date create;
    private Date update;
}
