package com.xupt.tmp.dto.signDto;

import lombok.Data;

import java.util.List;

@Data
public class KV {
    private long value;
    private String label;
    private List<KV> children;
}
