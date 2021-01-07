package cn.edu.zjut.common.controller;

import cn.edu.zjut.common.api.CommonPage;
import cn.edu.zjut.common.api.CommonResult;
import cn.edu.zjut.common.domain.*;
import cn.edu.zjut.common.service.ActivityService;
import cn.edu.zjut.common.service.CollegeService;
import cn.edu.zjut.common.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.hssf.record.NameCommentRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private UserService userService;

    @Autowired
    private CollegeService collegeService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityController.class);

    @ApiOperation("获取所有活动列表")
    @RequestMapping(value = "/activity/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Showac>> getActivityList() {
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
    public CommonResult updateActivity(@PathVariable("id") Long id, @RequestBody Showac showac) {
        CommonResult commonResult = CommonResult.success("1");
        try {

//        int count = activityService.updateActivity(id, activity);

            Activity activity = activityService.getActivity(id);
            Long directorId = userService.askIdByName(showac.getDirectorName());
            Long collegeId = collegeService.getCollegeId(showac.getCollegeName());
            System.out.println("更新指定id的活动  " + activity.toString() + directorId.toString() + collegeId.toString());
            activity.setDirector_id(directorId);
            activity.setCollege_id(collegeId);

            int count = activityService.updateActivity(id, activity);

            if (count == 1) {
                commonResult = CommonResult.success(activity);
                LOGGER.debug("updateActivity success:{}", activity);
            } else {
                commonResult = CommonResult.failed("操作失败");
                LOGGER.debug("updateActivity failed:{}", activity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return commonResult;

    }

    @ApiOperation("通过关键词查找")
    @RequestMapping(value = "/activity/askBykeywords", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<Showac>> askBykeywords(
            @RequestParam(value = "pageNum", defaultValue = "1") @ApiParam("页码") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") @ApiParam("每页数量") Integer pageSize,
            @RequestBody Keywords keywords) {
        List<Showac> activityList = activityService.askBykeywords(pageNum, pageSize, keywords);
        return CommonResult.success(CommonPage.restPage(activityList));
    }

    @ApiOperation("修改活动的负责人")
    @RequestMapping(value = "/activity/updateActivityDirector", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateActivityDirector(
            @RequestParam(value = "activityId") @ApiParam("活动的Id") Long activityId,
            @RequestParam(value = "directorName") @ApiParam("负责人的姓名") String directorName) {
        CommonResult commonResult;
        Long directorId = userService.askIdByName(directorName);
        Activity activity = activityService.getActivity(activityId);
        activity.setDirector_id(directorId);
        int flag = activityService.updateActivity(activityId, activity);
        if (flag == 1) {
            commonResult = CommonResult.success("更新成功");
        } else {
            commonResult = CommonResult.failed("操作失败");
        }
        return commonResult;
    }

    @ApiOperation("更新活动")
    @RequestMapping(value = "/activity/updateStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateStatus(@RequestBody Status status) {
        CommonResult commonResult;
        int count = activityService.updateActivityStatus(status);
        if (count == 1) {
            commonResult = CommonResult.success("更新成功");
        } else {
            commonResult = CommonResult.failed("操作失败");
        }
        return commonResult;
    }


    @ApiOperation("分页查询活动列表")
    @RequestMapping(value = "/activity/listPassed", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<Showac>> listActivityPassed(
            @RequestParam(value = "pageNum", defaultValue = "1") @ApiParam("页码") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") @ApiParam("每页数量") Integer pageSize) {
        List<Showac> activityList = activityService.listActivityPassed(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(activityList));
    }

    @ApiOperation("分页查询活动列表")
    @RequestMapping(value = "/activity/listFailed", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<Showac>> listActivityFailed(
            @RequestParam(value = "pageNum", defaultValue = "1") @ApiParam("页码") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") @ApiParam("每页数量") Integer pageSize) {
        List<Showac> activityList = activityService.listActivityFailed(pageNum, pageSize);
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

    @ApiOperation("获取指定举办人的活动")
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Showac>> getDirectorActivity(@PathVariable("userId") Long userId) {
        return CommonResult.success(activityService.getDirectorActivity(userId));
    }

}
