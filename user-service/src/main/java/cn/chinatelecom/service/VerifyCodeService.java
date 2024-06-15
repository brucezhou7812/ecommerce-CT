package cn.chinatelecom.service;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;

public interface VerifyCodeService {
    BufferedImage getVerifyCode(HttpServletRequest request);
    boolean checkVerifyCode(String code,HttpServletRequest request);
}
