package com.xupt.tmp.controller;

import com.xupt.tmp.common.Consts;
import com.xupt.tmp.dto.ResultMap;
import com.xupt.tmp.dto.gradeExtDto.GradeExtQuery;
import com.xupt.tmp.dto.gradeExtDto.GradeExtResult;
import com.xupt.tmp.model.GradeExt;
import com.xupt.tmp.service.GradeExtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = Consts.BASE_API_PATH + "/ext")
@Slf4j
public class GradeExtController {

    @Autowired
    private GradeExtService gradeExtService;

    @GetMapping
    public ResponseEntity getGradeExts(GradeExtQuery query, HttpServletRequest request) {
        List<GradeExtResult> list = gradeExtService.getGradeExts(query,request);
        return ResponseEntity.ok(new ResultMap().success().payload(list));
    }

    @PostMapping
    public ResponseEntity createGradeExt(@RequestBody GradeExt gradeExt) {
        gradeExtService.createGradeExt(gradeExt);
        return ResponseEntity.ok(new ResultMap().success());
    }

    @PutMapping
    public ResponseEntity updateGradeExt(@RequestBody GradeExt gradeExt) {
        gradeExtService.updateGradeExt(gradeExt);
        return ResponseEntity.ok(new ResultMap().success());
    }

    @GetMapping("info")
    public ResponseEntity getGradeExtInfo(GradeExtQuery query) {
        List<GradeExt> list = gradeExtService.getGradeExtsInfo(query);
        return ResponseEntity.ok(new ResultMap().success().payload(list));
    }

    @DeleteMapping
    public ResponseEntity getGradeExtInfo(long id) {
        gradeExtService.deleteGradeExt(id);
        return ResponseEntity.ok(new ResultMap().success());
    }
}
