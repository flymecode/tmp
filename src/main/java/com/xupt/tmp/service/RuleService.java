package com.xupt.tmp.service;

import com.xupt.tmp.model.Rule;
import com.xupt.tmp.model.User;

import java.util.List;

public interface RuleService {
    List<Rule> getRules(String username);

    void addRule(Rule rule, User user);
}
