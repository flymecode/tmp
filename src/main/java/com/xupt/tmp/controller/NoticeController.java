package com.xupt.tmp.controller;

import com.github.pagehelper.PageInfo;
import com.xupt.tmp.annotion.CurrentUser;
import com.xupt.tmp.common.Consts;
import com.xupt.tmp.dto.ResultMap;
import com.xupt.tmp.dto.noticeDto.NoticeCreate;
import com.xupt.tmp.dto.noticeDto.NoticeQuery;
import com.xupt.tmp.dto.noticeDto.NoticeUpdate;
import com.xupt.tmp.model.Notice;
import com.xupt.tmp.model.User;
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
    public ResponseEntity addNotice(@RequestBody NoticeCreate createNotice, HttpServletRequest request) {
        noticeService.addNotice(createNotice, request);
        return ResponseEntity.ok(new ResultMap().success());
    }

    @GetMapping("/list")
    public ResponseEntity getNotice(NoticeQuery query, HttpServletRequest request) {
        PageInfo<Notice> notices = noticeService.getNotices(query, request);
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

    @GetMapping("/info")
    public ResponseEntity getNotices(@CurrentUser User user) {
        List<Notice> list = noticeService.getNotices(user);
        return ResponseEntity.ok(new ResultMap().success().payloads(list));
    }

    @DeleteMapping
    public ResponseEntity deleteNotice(@RequestParam("id") Long id) {
        noticeService.deleteNotice(id);
        return ResponseEntity.ok(new ResultMap().success());
    }
}
