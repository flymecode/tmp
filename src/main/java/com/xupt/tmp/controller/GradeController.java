package com.xupt.tmp.controller;

import com.xupt.tmp.common.Consts;
import com.xupt.tmp.dto.ResultMap;
import com.xupt.tmp.dto.gradeDto.GradeQuery;
import com.xupt.tmp.dto.gradeDto.GradeResult;
import com.xupt.tmp.dto.gradeDto.GradeSum;
import com.xupt.tmp.dto.gradeDto.GradeUpdate;
import com.xupt.tmp.service.GradeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = Consts.BASE_API_PATH + "/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    /**
     * 获得考试成绩
     * @return
     */
    @GetMapping
    public ResponseEntity getContestGrade() {
        return ResponseEntity.ok(new ResultMap().success());
    }


    @GetMapping("/info")
    public ResponseEntity getContestGradeInfo(@Param("id") Long id) {
        GradeResult result = gradeService.getGradeInfo(id);
        return ResponseEntity.ok(new ResultMap().success().payload(result));
    }

    /**
     * 提交考试成绩
     * @param gradeUpdate
     * @return
     */
    @PostMapping
    public ResponseEntity commitGrade(@RequestBody GradeUpdate gradeUpdate){
        gradeService.commitGrade(gradeUpdate);
        return ResponseEntity.ok(new ResultMap().success());
    }

    /**
     * 获取最终成绩
     * @param query
     * @return
     */
    @GetMapping("/sum")
    public ResponseEntity getFinalGrade(@RequestBody GradeQuery query) {
        List<GradeSum> gradeSums = gradeService.getGradeSum(query);
        return ResponseEntity.ok(new ResultMap().success().payload(gradeSums));
    }
}
