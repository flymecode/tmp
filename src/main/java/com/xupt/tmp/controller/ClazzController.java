package com.xupt.tmp.controller;

import com.xupt.tmp.common.Consts;
import com.xupt.tmp.dto.ResultMap;
import com.xupt.tmp.dto.clazzDto.CreateClazz;
import com.xupt.tmp.dto.userDto.UserUpload;
import com.xupt.tmp.model.Clazz;
import com.xupt.tmp.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = Consts.BASE_API_PATH + "/clazz")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @GetMapping
    public ResponseEntity getAllClazz(@RequestParam("id") long courseId) {
        List<Clazz> list = clazzService.getClazzs(courseId);
        return ResponseEntity.ok(new ResultMap().success().payloads(list));
    }


    @PostMapping
    public ResponseEntity createClazz(@RequestBody CreateClazz createClazz) {
        clazzService.createClazz(createClazz);
        return ResponseEntity.ok(new ResultMap().success());
    }

    @GetMapping("/info")
    public ResponseEntity getClazzByClazzId(@RequestParam("clazzId") long clazzId) {
        List<UserUpload> studnets = clazzService.getClazzStudnets(clazzId);
        return ResponseEntity.ok(new ResultMap().success().payloads(studnets));
    }
}
