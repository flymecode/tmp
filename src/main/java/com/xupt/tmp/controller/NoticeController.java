package com.xupt.tmp.controller;

import com.xupt.tmp.common.Consts;
import com.xupt.tmp.dto.ResultMap;
import com.xupt.tmp.dto.noticeDto.CreateNotice;
import com.xupt.tmp.dto.noticeDto.NoticeUpdate;
import com.xupt.tmp.model.Notice;
import com.xupt.tmp.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = Consts.BASE_API_PATH + "/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @PostMapping
    public ResponseEntity addNotice(@RequestBody CreateNotice createNotice, HttpServletRequest request) {
        noticeService.addNotice(createNotice, request);
        return ResponseEntity.ok(new ResultMap().success());
    }

    @GetMapping("/list")
    public ResponseEntity getNotice(int page, int limit, HttpServletRequest request) {
        List<Notice> notices = noticeService.getNotices(page, limit, request);
        return ResponseEntity.ok(new ResultMap().success().payload(notices));
    }

    @PutMapping
    public ResponseEntity updateNotice(NoticeUpdate noticeUpdate) {
        noticeService.updateNotice(noticeUpdate);
        return ResponseEntity.ok(new ResultMap().success());
    }

    @GetMapping
    public ResponseEntity getNoticeByCourseIds(List<Long> courseIds) {
        List<Notice> result = noticeService.getNoticeByCourseIds(courseIds);
        return ResponseEntity.ok(new ResultMap().success().payloads(result));
    }
}
