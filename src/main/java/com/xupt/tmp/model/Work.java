package com.xupt.tmp.model;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class Work {

    private int wId;
    private int cId;
    private String title;
    private String body;
    private int status;

    private DateTime create;
    private DateTime update;
    private DateTime end;
}
