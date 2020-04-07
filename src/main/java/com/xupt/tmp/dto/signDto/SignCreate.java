package com.xupt.tmp.dto.signDto;

import lombok.Data;

@Data
public class SignCreate {
    private String name;
    private Long[] date;
    private Long[] value;
}
