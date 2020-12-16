package cn.edu.zjut.common.controller;

import cn.edu.zjut.common.api.CommonPage;
import cn.edu.zjut.common.api.CommonResult;
import cn.edu.zjut.common.domain.Activity;
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
@Api(tags = "ActivityController", description = "活动管理")
@CrossOrigin // 允许所有ip跨域
@Controller
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityController.class);

    @ApiOperation("获取所有活动列表")
    @RequestMapping(value = "/activity/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Activity>> getActivityList() {
        return CommonResult.success(activityService.listAllActivity());
    }

    @ApiOperation("添加活动")
    @RequestMapping(value = "/activity/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createActivity(@RequestBody Activity activity) {
        CommonResult commonResult;
        int count = activityService.createActivity(activity);
        if (count == 1) {
            commonResult = CommonResult.success(activity);
            LOGGER.debug("createActivity success:{}", activity);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createActivity failed:{}", activity);
        }
        return commonResult;
    }

    @ApiOperation("删除指定id的活动")
    @RequestMapping(value = "/activity/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteActivity(@PathVariable("id") Long id) {
        int count = activityService.deleteActivity(id);
        if (count == 1) {
            LOGGER.debug("deleteActivity success :id={}", id);
            return CommonResult.success(null);
        } else {
            LOGGER.debug("deleteActivity failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }

    @ApiOperation("更新指定id的活动")
    @RequestMapping(value = "/activity/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateActivity(@PathVariable("id") Long id, @RequestBody Activity activity) {
        CommonResult commonResult;
        int count = activityService.updateActivity(id, activity);
        if (count == 1) {
            commonResult = CommonResult.success(activity);
            LOGGER.debug("updateActivity success:{}", activity);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("updateActivity failed:{}", activity);
        }
        return commonResult;
    }

    @ApiOperation("分页查询活动列表")
    @RequestMapping(value = "/activity/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<Activity>> listActivity(
            @RequestParam(value = "pageNum", defaultValue = "1") @ApiParam("页码") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") @ApiParam("每页数量") Integer pageSize) {
        List<Activity> activityList = activityService.listActivity(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(activityList));
    }

    @ApiOperation("获取指定id的活动")
    @RequestMapping(value = "/activity/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Activity> activity(@PathVariable("id") Long id) {
        if (activityService.getActivity(id) != null) {
            return CommonResult.success(activityService.getActivity(id));
        } else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/activity/signed", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<String> signedActivity(
            @RequestParam(value = "id") long id,
            @RequestParam(value = "date") long date) {
        long now_date = new Date().getTime();
        System.out.println(id + "  " + date + " 11 " + now_date);
        if (now_date - date > 0 && now_date - date < 300000 && activityService.getActivity(id) != null) {
            //数据库代码
            return CommonResult.success("签到成功");
        } else {
            return CommonResult.failed("签到失败");
        }
    }

}
