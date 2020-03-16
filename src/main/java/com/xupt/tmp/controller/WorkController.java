package com.xupt.tmp.controller;

import com.xupt.tmp.annotion.CurrentUser;
import com.xupt.tmp.common.Consts;
import com.xupt.tmp.model.User;
import com.xupt.tmp.model.Work;
import com.xupt.tmp.dto.ResultMap;
import com.xupt.tmp.dto.workDto.WorkCreate;
import com.xupt.tmp.service.WorkService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


/**
 * 任务控制
 */
@RestController
@RequestMapping(value = Consts.BASE_API_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkController extends BaseController {

    @Autowired
    private WorkService workService;

    @ApiOperation("create work")
    @PostMapping("/work")
    public ResponseEntity createWork(@ApiIgnore @CurrentUser User user,
                                     @Valid @RequestBody WorkCreate workCreate,
                                     @ApiIgnore BindingResult bindingResult,
                                     HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            ResultMap resultMap = new ResultMap().fail().message(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.status(resultMap.getCode()).body(resultMap);
        }
        Work work = workService.createWork(workCreate, user);
        return ResponseEntity.ok(new ResultMap(tokenUtils).successAndRefreshToken(request).payload(work));
    }
}
