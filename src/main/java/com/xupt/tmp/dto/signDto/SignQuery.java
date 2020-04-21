package com.xupt.tmp.dto.signDto;

import com.xupt.tmp.dto.BasePageInfo;
import lombok.Data;

@Data
public class SignQuery extends BasePageInfo {
    private long clazzId;
    private long courseId;
    private String username;
}
