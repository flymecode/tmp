package com.xupt.tmp.domain;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class Work {
    private int wId;
    private int cId;
    private int status;
    private String body;
    private DateTime start;
    private DateTime update;
    private DateTime end;
}
