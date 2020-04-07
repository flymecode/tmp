package com.xupt.tmp.service;

import com.xupt.tmp.dto.contestDto.ContestResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ContestServiceTest {
    @Autowired
    private ContestService contestService;

    @Test
    void createContest() {
    }

    @Test
    void updateContest() {
    }

    @Test
    void deleteContest() {
    }

    @Test
    void getContestsByCourseId() {
        List<Long> list = new ArrayList<>();
        list.add(2L);
        List<ContestResult> contestsByCourseId = contestService.getContestsByCourseId(list);
        System.out.println(contestsByCourseId);
    }
}
