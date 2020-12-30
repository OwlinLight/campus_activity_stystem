package cn.edu.zjut.common.controller;

import cn.edu.zjut.common.api.CommonPage;
import cn.edu.zjut.common.api.CommonResult;
import cn.edu.zjut.common.domain.Participation;
import cn.edu.zjut.common.service.ParticipationService;
import cn.edu.zjut.common.utils.POIUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.List;


/**
 * Created by iris on 2020/12/27.
 */
@CrossOrigin // 允许所有ip跨域
@Api(tags = "ParticipationController", description = "具体一场活动管理")
@Controller
public class ParticipationController {
    @Autowired
    private ParticipationService participationService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityController.class);

    @ApiOperation("活动报名")
    @RequestMapping(value = "/activity/register", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<String> registerActivity(
            @RequestParam(value = "staffId") @ApiParam("学号/工号") Long staffId,
            @RequestParam(value = "activityId") @ApiParam("活动id") Long activityId) {
        CommonResult commonResult;
        int count = participationService.registerActivity(staffId, activityId);
        if (count == 1) {
            commonResult = CommonResult.success("报名成功");
        } else {
            commonResult = CommonResult.failed("报名失败");
        }
        return commonResult;
    }

    @ApiOperation("活动签到")
    @RequestMapping(value = "/activity/enroll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> enrollActivity(
            @RequestBody Participation participation) {
        CommonResult commonResult;
        System.out.println(participation);
        boolean enrollResult = participationService.enrollActivity(participation);
        if (enrollResult) {
            commonResult = CommonResult.success("签到成功");
        } else {
            commonResult = CommonResult.failed("签到失败");
        }
        return commonResult;
    }

    @ApiOperation("生成签到二维码")
    @RequestMapping(value = "/activity/qrcode", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<String> generateQRCode(
            @RequestParam(value = "activityId") @ApiParam("活动id") Long activityId) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            String url = "http://192.168.31.226/enroll.jsp?id=" + activityId;
            System.out.println(url);
            BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 600, 600);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            Base64.Encoder encoder = Base64.getEncoder();
            String text = encoder.encodeToString(outputStream.toByteArray());
            return CommonResult.success(text);
        } catch (Exception e) {
            return CommonResult.failed();
        }
    }

    @ApiOperation("分页展示一场活动参加情况")
    @GetMapping("/activity/listParticipation/{activityId}")
    @ResponseBody
    public CommonResult<CommonPage<Participation>> listParticipation(
            @RequestParam(value = "pageNum", defaultValue = "1") @ApiParam("页码") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") @ApiParam("每页数量") Integer pageSize,
            @PathVariable("activityId") Long activityId) {
        List<Participation> participationList = (List<Participation>) participationService.listParticipation(pageNum, pageSize, activityId);
        return CommonResult.success(CommonPage.restPage(participationList));
    }

    @ApiOperation("导出活动参加情况为Excel")
    @GetMapping("/activity/export/{activityId}")
    @ResponseBody
    public ResponseEntity<byte[]> exportData(@PathVariable("activityId") Long activityId) {
        List<Participation> participationList = (List<Participation>) participationService.listAllParticipation(activityId);
        return POIUtils.employee2Excel(participationList);
    }

}
