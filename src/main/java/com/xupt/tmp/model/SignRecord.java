package com.xupt.tmp.model;

import com.xupt.tmp.dto.userDto.UserUpload;
import lombok.Data;

import java.util.Date;

@Data
public class SignRecord {
    /**
     * 签到Id
     */
    private long signId;
    /**
     * 签到状态 1-签到 0-未签到
     */
    private int status;
    /**
     * 签到时间
     */
    private Date signTime;
    /**
     * 用户Id
     */
    private String username;

    /**
     * 用户姓名
     */
    private String name;

    public SignRecord() {}
    public SignRecord(UserUpload userUpload) {
        this.username = userUpload.getUsername();
        this.name = userUpload.getName();
        this.status = 0;
    }
}
