package com.xupt.tmp.dto.replyDto;

import com.xupt.tmp.dto.BasePageInfo;
import lombok.Data;

@Data
public class ReplyQuery extends BasePageInfo {
    long[] value;
}
