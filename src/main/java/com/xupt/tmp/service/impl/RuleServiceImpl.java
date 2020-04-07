package com.xupt.tmp.service.impl;

import com.xupt.tmp.mapper.RuleMapper;
import com.xupt.tmp.model.Rule;
import com.xupt.tmp.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleServiceImpl implements RuleService {
    @Autowired
    private RuleMapper ruleMapper;
    @Override
    public List<Rule> getRules(String username) {
        return ruleMapper.selectRules(username);
    }
}
