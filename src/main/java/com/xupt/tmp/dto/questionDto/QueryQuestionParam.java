package com.xupt.tmp.dto.questionDto;

import com.xupt.tmp.dto.BasePageInfo;
import lombok.Data;

@Data
public class QueryQuestionParam extends BasePageInfo {
    Integer type;
    Integer courseId;
    String sort;
    Integer difficulty;
}
