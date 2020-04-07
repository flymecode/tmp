package com.xupt.tmp.dto.gradeDto;

import lombok.Data;

import java.util.List;

@Data
public class GradeUpdate {
    private long id;
    private List<Score> scores;

    @Data
    public class Score {
        private long id;
        private int score;

        @Override
        public String toString() {
            return "{" +
                    "id:" + id +
                    ", score:" + score +
                    '}';
        }
    }
}
