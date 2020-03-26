package com.xupt.tmp.model;

import lombok.Data;

import java.util.Date;

@Data
public class Work {

    private int wId;
    private int cId;
    private String title;
    private String body;
    private int status;

    private Date create;
    private Date update;
    private Date end;
}
