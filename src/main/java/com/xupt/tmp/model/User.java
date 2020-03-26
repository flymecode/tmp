package com.xupt.tmp.model;

import com.xupt.tmp.dto.userDto.TokenDetail;
import lombok.Data;

import java.util.Date;

@Data
public class User extends TokenDetail{
    private long id;
    private String name;
    private String email;
    private int sex;
    private int type;
    private Boolean active = false;
    private Date createTime;
    private Date updateTime;
    private String avatar;
    private String roles;
}
