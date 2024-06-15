package cn.chinatelecom.service.impl;

import cn.chinatelecom.service.VerifyCodeService;
import cn.chinatelecom.utils.CommonUtils;
import com.google.code.kaptcha.Producer;
import com.mysql.cj.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class KaptchaVerifyCodeServiceImpl implements VerifyCodeService {
    @Autowired
    private Producer kaptchaProducer;

    @Autowired
    private StringRedisTemplate redisTemplate;
    private final long expireTimeForKaptcha = 60*10*1000;
    @Override
    public BufferedImage getVerifyCode(HttpServletRequest request) {
        String verifyCodeText = kaptchaProducer.createText();
        log.info("verify code is generated: "+verifyCodeText);
        String kaptcha = this.getKaptchaKey(request);
        redisTemplate.opsForValue().set(kaptcha,verifyCodeText,expireTimeForKaptcha, TimeUnit.MILLISECONDS);
        BufferedImage bufferedImage = kaptchaProducer.createImage(verifyCodeText);
        return bufferedImage;
    }

    @Override
    public boolean checkVerifyCode(String code,HttpServletRequest request) {
        String cacheKey = this.getKaptchaKey(request);
        String cacheCode = redisTemplate.opsForValue().get(cacheKey);
        if(!StringUtils.isNullOrEmpty(cacheCode) && cacheCode.equalsIgnoreCase(code)){
            redisTemplate.delete(cacheKey);
            return true;
        }
        return false;
    }

    private String getKaptchaKey(HttpServletRequest request){
        String ip = CommonUtils.getIpAddr(request);
        String userAgent = request.getHeader("User-Agent");
        String kaptchaKey = "User-service:kaptcha:"+CommonUtils.MD5(ip+userAgent);
        return kaptchaKey;
    }
}
