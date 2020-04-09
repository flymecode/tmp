package com.xupt.tmp.dto.contestDto;

import lombok.Data;

@Data
public class ContestQuery {
    private int type;
    private int page;
    private int limit;
    private long[] value;
}
