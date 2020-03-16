package com.xupt.tmp.service.impl;

import com.xupt.tmp.dto.contestDto.ContestCreate;
import com.xupt.tmp.model.Contest;
import com.xupt.tmp.service.ContestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ContestServiceImpl implements ContestService {

    @Override
    public void addContest(ContestCreate contestCreate) {

    }

    @Override
    public boolean updateContest(Contest contest) {
        return false;
    }

    @Override
    public boolean deleteContest(int id) {
        return false;
    }
}
