package cn.edu.zjut.common.controller;

import cn.edu.zjut.common.api.CommonResult;
import cn.edu.zjut.common.domain.Activity;
import cn.edu.zjut.common.domain.ActivityInfo;
import cn.edu.zjut.common.domain.Comment;
import cn.edu.zjut.common.domain.Showac;
import cn.edu.zjut.common.service.ActivityInfoService;
import cn.edu.zjut.common.service.CommentService;
import cn.edu.zjut.common.service.impl.CommentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 评价管理Controller
 */
@Api(tags = "CommentController", description = "评价管理")
@CrossOrigin // 允许所有ip跨域
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    @ApiOperation("获取所有评论列表")
    @RequestMapping(value = "/activity/comment/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Comment>> getActivityList(@PathVariable("id") Long id) {
        if (commentService.listActivityComment(id)!=null){
            return CommonResult.success(commentService.listActivityComment(id));
        }
        else{
            return CommonResult.failed();
        }
    }

    @ApiOperation("添加评论")
    @RequestMapping(value = "/activity/comment/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createActivity(@RequestBody Comment comment) {
        CommonResult commonResult;
        int count = commentService.createComment(comment);
        if (count == 1) {
            commonResult = CommonResult.success(comment);
            LOGGER.debug("createActivity success:{}", comment);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createActivity failed:{}", comment);
        }
        return commonResult;
    }
}


