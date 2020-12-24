package cn.edu.zjut.common.controller;

import cn.edu.zjut.common.api.CommonResult;
import cn.edu.zjut.common.domain.Activity;
import cn.edu.zjut.common.domain.College;
import cn.edu.zjut.common.service.ActivityService;
import cn.edu.zjut.common.service.CollegeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "CollegeController", description = "学院信息")
@CrossOrigin // 允许所有ip跨域
@Controller
public class CollegeController {
    @Autowired
    private CollegeService collegeService;

    @ApiOperation("获取所有学院信息")
    @RequestMapping(value = "/activity/college/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<College>> getActivityList() {
        return CommonResult.success(collegeService.listAllCollege());
    }
}
