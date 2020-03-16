package com.xupt.tmp.model;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class SignTask {
    private int sid;
    private String name;
    private DateTime createTime;
    private DateTime endTime;
}
