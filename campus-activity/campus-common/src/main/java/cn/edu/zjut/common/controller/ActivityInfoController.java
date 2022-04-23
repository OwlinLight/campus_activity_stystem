package cn.edu.zjut.common.controller;

import cn.edu.zjut.common.api.CommonResult;
import cn.edu.zjut.common.domain.*;
import cn.edu.zjut.common.service.ActivityInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


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
    @RequestMapping(value = "/activity/activityInfo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<ActivityInfo> activity(@PathVariable("id") Long id) {
        if (activityInfoService.getActivityInfo(id) != null) {
            return CommonResult.success(activityInfoService.getActivityInfo(id));
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("更新指定id的限定人数")
    @RequestMapping(value = "/activity/activityInfo/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateActivity(@RequestParam(value = "id") Long id,
                                       @RequestParam(value = "limit") Integer limit) {
        CommonResult commonResult;
        int count = activityInfoService.updatePeopleLimit(id, limit);
        if (count == 1) {
            commonResult = CommonResult.success(limit);
            LOGGER.debug("updateActivity success:{}", limit);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("updateActivity failed:{}", limit);
        }
        return commonResult;
    }
}
