package com.xupt.tmp.model;

import lombok.Data;

@Data
public class UCCRelation {
    private long id;
    private long courseId;
    private long clazzId;
    private String username;
    private String name;
    private String courseName;
    private String clazzName;
}
