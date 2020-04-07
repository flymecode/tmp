package com.xupt.tmp.mapper;

import com.xupt.tmp.model.Rule;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RuleMapper {

    @Select("select * from rule where username = #{username}")
    List<Rule> selectRules(String username);

    @Select("select * from rule where id = #{ruleId}")
    Rule selectRulesById(@Param("ruleId") int ruleId);
}
