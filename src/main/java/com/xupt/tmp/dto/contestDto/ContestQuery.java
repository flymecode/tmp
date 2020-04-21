package com.xupt.tmp.dto.contestDto;

import com.xupt.tmp.dto.BasePageInfo;
import lombok.Data;

@Data
public class ContestQuery extends BasePageInfo {
    private int type;
    private long clazzId;
    private long courseId;
    private String username;
}
