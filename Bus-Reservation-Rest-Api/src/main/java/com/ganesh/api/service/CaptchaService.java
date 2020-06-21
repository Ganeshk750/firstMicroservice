package com.ganesh.api.service;

import com.ganesh.api.entity.Captcha;

public interface CaptchaService {
    CaptchaDto createCaptcha();
    boolean isCaptchaValid(Captcha captcha);
}
