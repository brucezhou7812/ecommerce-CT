package cn.chinatelecom.controller;

import cn.chinatelecom.enums.BizCodeEnum;
import cn.chinatelecom.enums.SendCodeEnum;
import cn.chinatelecom.service.MailService;
import cn.chinatelecom.service.NotifyService;
import cn.chinatelecom.service.VerifyCodeService;
import cn.chinatelecom.utils.JSonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("/api/user/v1/")
@Api(tags = "Notify Controller")
@Slf4j
public class NotifyController {
    @Autowired
    private VerifyCodeService verifyCodeService;
    @Autowired
    private NotifyService notifyService;

    @GetMapping("kaptcha")
    @ApiOperation(value = "Get Graphic verify code")
    public void getKaptcha(HttpServletRequest request, HttpServletResponse response){
        BufferedImage kaptcha = verifyCodeService.getVerifyCode(request);
        try {
            OutputStream outputStream = response.getOutputStream();
            ImageIO.write(kaptcha,"jpg",outputStream);
            outputStream.flush();
            outputStream.close();
        }catch (IOException e){
            log.error("Get Graphic verify code failure: "+e);
        }
    }

    @GetMapping("send_verify_code")
    @ApiOperation("Send verify code through email")
    public JSonData sendVerifyCodeThroughMail(@ApiParam(value="Graphic verify code",required = true) @RequestParam(value="code",required = true) String kaptcha,
                                              @ApiParam(value="Verify code recipient",required = true) @RequestParam(value="to",required = true) String to,
                                              HttpServletRequest request){
        if(verifyCodeService.checkVerifyCode(kaptcha,request)){
            return notifyService.sendCode(SendCodeEnum.REGISTER_CODE,to);
        }else{
            return JSonData.buildResult(BizCodeEnum.CODE_ERROR);
        }
    }
}
