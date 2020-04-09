package com.xupt.tmp.controller;

import com.xupt.tmp.annotion.CurrentUser;
import com.xupt.tmp.common.Consts;
import com.xupt.tmp.dto.ResultMap;
import com.xupt.tmp.dto.gradeExtDto.GradeExtQuery;
import com.xupt.tmp.model.GradeExt;
import com.xupt.tmp.model.Reply;
import com.xupt.tmp.model.User;
import com.xupt.tmp.service.GradeExtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = Consts.BASE_API_PATH + "/ext")
@Slf4j
public class GradeExtController {
    @Autowired
    private GradeExtService gradeExtService;

    @GetMapping
    public ResponseEntity getGradeExts(@CurrentUser User user, GradeExtQuery query) {
        List<Reply> replies = gradeExtService.getGradeExts(user.getUsername(), query);
        return ResponseEntity.ok(new ResultMap().success().payload(replies));
    }

    @PostMapping
    public ResponseEntity createGradeExt(GradeExt gradeExt) {
        gradeExtService.createGradeExt(gradeExt);
        return ResponseEntity.ok(new ResultMap().success());
    }

    @PutMapping
    public ResponseEntity updateGradeExt(GradeExt gradeExt) {
        gradeExtService.updateGradeExt(gradeExt);
        return ResponseEntity.ok(new ResultMap().success());
    }
}
