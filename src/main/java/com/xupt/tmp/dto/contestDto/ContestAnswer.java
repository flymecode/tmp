package com.xupt.tmp.dto.contestDto;

import lombok.Data;

import java.util.List;

@Data
public class ContestAnswer {
    /**
     * 测试id
     */
    private long contestId;
    /**
     * 试卷id
     */
    private long questionPaperId;
    /**
     * 问题
     */
    private String quesitons;
    /**
     * 回答
     */
    private List<Node> answer;

    @Data
    public
    class Node {
        Long id;
        String r;

        @Override
        public String toString() {
            return "{id:" + id + ",r:" + r + "}";
        }
    }
}
