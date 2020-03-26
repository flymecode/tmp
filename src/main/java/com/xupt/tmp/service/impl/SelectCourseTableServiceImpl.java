package com.xupt.tmp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xupt.tmp.dto.courseTableDto.CourseTableCreate;
import com.xupt.tmp.mapper.SelectCourseTableMapper;
import com.xupt.tmp.model.SelectCourseTable;
import com.xupt.tmp.service.SelectCourseTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectCourseTableServiceImpl implements SelectCourseTableService {

    @Autowired
    private SelectCourseTableMapper selectCourseTableMapper;

    @Override
    public List<Long> getCourseTable(String username) {
        String courseTable = selectCourseTableMapper.selectCourseTable(username);
        return JSONObject.parseArray(courseTable, Long.class);
    }

    @Override
    public void addCourseTable(CourseTableCreate courseTableCreate) {
        SelectCourseTable selectCourseTable = new SelectCourseTable();
        selectCourseTable.setId(courseTableCreate.getId());
        selectCourseTable.setUsername(courseTableCreate.getUsername());
        selectCourseTable.setCourseIds(JSONObject.toJSONString(courseTableCreate.getCourseIds()));
        selectCourseTableMapper.insert(selectCourseTable);
    }
}
