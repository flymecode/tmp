package com.xupt.tmp.model;

import com.xupt.tmp.dto.userDto.TokenDetail;
import lombok.Data;
import org.joda.time.DateTime;

@Data
public class User extends TokenDetail{
    private long id;
    private String name;
    private String email;
    private int sex;
    private int type;
    private Boolean active = false;
    private DateTime createTime;
    private DateTime updateTime;
    private String avatar;
    private String roles;
}
