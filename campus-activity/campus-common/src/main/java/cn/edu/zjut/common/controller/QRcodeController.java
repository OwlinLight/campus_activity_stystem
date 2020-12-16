package cn.edu.zjut.common.controller;

import cn.edu.zjut.common.api.CommonResult;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Date;

@CrossOrigin // 允许所有ip跨域
@Controller
public class QRcodeController {
    @RequestMapping(value = "/activity/getQRcode", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<String> getActivityList(
            @RequestParam(value = "id") String id) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            String url = "http://localhost/activity/signed?id=" + id + "&date=" + new Date().getTime();
            System.out.println(url);
            BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 600, 600);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            Base64.Encoder encoder = Base64.getEncoder();

            String text = encoder.encodeToString(outputStream.toByteArray());

            return CommonResult.success(text);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        QRcodeController qRcodeController = new QRcodeController();
        System.out.println(qRcodeController.getActivityList("5"));
    }
}
