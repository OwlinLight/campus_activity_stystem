package cn.edu.zjut.common.controller;

import cn.edu.zjut.common.api.CommonResult;
import cn.edu.zjut.common.dto.OssCallbackResult;
import cn.edu.zjut.common.dto.OssPolicyResult;
import cn.edu.zjut.common.service.impl.OssServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Oss相关操作接口
 */
@Controller
@Api(tags = {"OssController"}, description = "Oss管理")
@RequestMapping({"/aliyun/oss"})
public class OssController {
    @Autowired
    private OssServiceImpl ossService;

    public OssController() {
    }

    @ApiOperation("oss上传签名生成")
    @RequestMapping(
            value = {"/policy"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public CommonResult<OssPolicyResult> policy() {
        OssPolicyResult result = this.ossService.policy();
        return CommonResult.success(result);
    }

    @ApiOperation("oss上传成功回调")
    @RequestMapping(
            value = {"callback"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public CommonResult<OssCallbackResult> callback(HttpServletRequest request) {
        OssCallbackResult ossCallbackResult = this.ossService.callback(request);
        return CommonResult.success(ossCallbackResult);
    }
}
