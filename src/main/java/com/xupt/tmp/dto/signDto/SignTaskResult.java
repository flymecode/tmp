package com.xupt.tmp.dto.signDto;

import com.xupt.tmp.model.SignTask;
import lombok.Data;

@Data
public class SignTaskResult {
    /**
     * 签到id
     */
    private long id;
    /**
     * 课程Id
     */
    private long courseId;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 创建人
     */
    private String createId;
    /**
     * 班级Id
     */
    private long clazzId;
    /**
     * 班级名称
     */
    private String clazzName;
    /**
     * 签到名称
     */
    private String name;

    private long curTime;
    /**
     * 开始时间
     */
    private long startTime;
    /**
     * 结束时间
     */
    private long endTime;

    public SignTaskResult(SignTask signTask) {
        this.id = signTask.getId();
        this.courseId = signTask.getCourseId();
        this.clazzId = signTask.getClazzId();
        this.curTime = System.currentTimeMillis();
        this.startTime = signTask.getStartTime().getTime();
        this.endTime = signTask.getEndTime().getTime();
        this.name = signTask.getName();
        this.courseName = signTask.getCourseName();
        this.clazzName = signTask.getClazzName();
    }
}
