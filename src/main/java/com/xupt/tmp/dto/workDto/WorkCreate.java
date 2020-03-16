package com.xupt.tmp.dto.workDto;

import lombok.Data;
import org.joda.time.DateTime;

import javax.validation.constraints.NotBlank;

@Data
public class WorkCreate {
    @NotBlank(message = "title cannot be EMPTY")
    private String title;
    @NotBlank(message = "body cannot be EMPTY")
    private DateTime createTime;

    private DateTime endTime;
}
