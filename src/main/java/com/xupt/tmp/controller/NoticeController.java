package com.xupt.tmp.controller;

import com.xupt.tmp.common.Consts;
import com.xupt.tmp.dto.ResultMap;
import com.xupt.tmp.dto.noticeDto.CreateNotice;
import com.xupt.tmp.model.Notice;
import com.xupt.tmp.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Consts.BASE_API_PATH + "/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @PostMapping
    public ResponseEntity addNotice(CreateNotice createNotice) {
        Notice notice = noticeService.addNotice(createNotice);
        return ResponseEntity.ok(new ResultMap());
    }
}
