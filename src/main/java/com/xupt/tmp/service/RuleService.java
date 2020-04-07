package com.xupt.tmp.service;

import com.xupt.tmp.model.Rule;

import java.util.List;

public interface RuleService {
    List<Rule> getRules(String username);
}
