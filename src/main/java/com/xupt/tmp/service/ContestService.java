package com.xupt.tmp.service;

import com.xupt.tmp.dto.contestDto.ContestCreate;
import com.xupt.tmp.model.Contest;

public interface ContestService {
    void addContest(ContestCreate contestCreate);

    boolean updateContest(Contest contest);

    boolean deleteContest(int id);
}
