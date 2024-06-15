package cn.chinatelecom.service;

import cn.chinatelecom.enums.SendCodeEnum;
import cn.chinatelecom.utils.JSonData;

public interface NotifyService {
    JSonData sendCode(SendCodeEnum sendCodeEnum, String to);
}
