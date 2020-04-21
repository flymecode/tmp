package com.xupt.tmp.model;

import com.xupt.tmp.dto.userDto.TokenDetail;
import lombok.Data;

import java.util.Date;

@Data
public class User extends TokenDetail {
    private long id;
    private String name;
    private String email;
    private int sex;
    private int type;
    private Boolean active = false;
    private String avatar;
    private String roles;
    private Date updateTime;
    private Date createTime;
}
