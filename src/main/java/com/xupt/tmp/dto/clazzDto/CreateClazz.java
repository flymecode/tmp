package com.xupt.tmp.dto.clazzDto;

import com.alibaba.fastjson.annotation.JSONField;
import com.xupt.tmp.dto.userDto.UserUpload;
import lombok.Data;

import java.util.List;

@Data
public class CreateClazz {
    /**
     * 课程id
     */
    @JSONField(name = "id")
    private Long courseId;
    /**
     * 课程名称
     */
    private String name;
    /**
     * 学生信息
     */
    @JSONField(name = "tableData")
    private List<UserUpload> students;
}
