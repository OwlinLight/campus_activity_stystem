package cn.edu.zjut.common.controller;

import cn.edu.zjut.common.api.CommonPage;
import cn.edu.zjut.common.api.CommonResult;
import cn.edu.zjut.common.domain.Activity;
import cn.edu.zjut.common.domain.ActivityInfo;
import cn.edu.zjut.common.domain.Keywords;
import cn.edu.zjut.common.domain.Status;
import cn.edu.zjut.common.service.ActivityInfoService;
import cn.edu.zjut.common.service.ActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


/**
 * 活动管理Controller
 * Created by iris on 2020/12/11.
 */
@Api(tags = "ActivityInfoController", description = "活动详细信息管理")
@CrossOrigin // 允许所有ip跨域
@Controller
public class ActivityInfoController {
    @Autowired
    private ActivityInfoService activityInfoService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityInfoController.class);


    @ApiOperation("获取指定id的活动详细信息")
    @RequestMapping(value = "/activityInfo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<ActivityInfo> activity(@PathVariable("id") Long id) {
        if (activityInfoService.getActivityInfo(id) != null) {
            return CommonResult.success(activityInfoService.getActivityInfo(id));
        } else {
            return CommonResult.failed();
        }
    }


    
    public static void main(String[] args) {
        ActivityInfoController activityController = new ActivityInfoController();

    }
}
