package cn.chinatelecom.service.impl;

import cn.chinatelecom.constant.ConstantCommerceClass;
import cn.chinatelecom.enums.BizCodeEnum;
import cn.chinatelecom.enums.SendCodeEnum;
import cn.chinatelecom.service.MailService;
import cn.chinatelecom.service.NotifyService;
import cn.chinatelecom.utils.CommonUtils;
import cn.chinatelecom.utils.JSonData;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class NotifyServiceImpl implements NotifyService {
    @Autowired
    MailService mailService;
    @Autowired
    StringRedisTemplate redisTemplate;
    private final String SUBJECT = "eCommerce verification code";
    private final String CONTEXT = "eCommerce verification code is %s. It is valid in 60 seconds,never tell to others!";
    @Override
    public JSonData sendCode(SendCodeEnum sendCodeEnum, String to) {
        String cacheKey = String.format(ConstantCommerceClass.KEY_IN_REDIS_VERIFICATION_CODE,sendCodeEnum.name(),to);
        String cacheCode = redisTemplate.opsForValue().get(cacheKey);
        if(!StringUtils.isNullOrEmpty(cacheCode)){
            String lastTimeStamp = cacheCode.split("_")[1];
            Long lastTime = Long.parseLong(lastTimeStamp);
            Long currentTime = CommonUtils.getTimestamp();
            Long ttl = currentTime - lastTime;
            if(ttl < ConstantCommerceClass.EXPIRE_TIME_FOR_VERIFICATION_CODE_TIME)
                return JSonData.buildResult(BizCodeEnum.CODE_LIMITED);
        }
        String code = CommonUtils.getRandomCode(6);
        String codeWithTimestamp = code+"_"+CommonUtils.getTimestamp();
        redisTemplate.opsForValue().set(cacheKey,codeWithTimestamp,ConstantCommerceClass.EXPIRE_TIME_FOR_VERIFICATION_CODE_TIME, TimeUnit.SECONDS);
        String content = String.format(CONTEXT,code);
        if(CommonUtils.isEmail(to)){
            mailService.sendMail(to,content,SUBJECT);
            return JSonData.buildSuccess(content);
        }else if(CommonUtils.isPhone(to)){

        }
        return JSonData.buildResult(BizCodeEnum.CODE_TO_ERROR);
    }
}
