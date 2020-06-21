package com.ganesh.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ganesh.api.dto.CaptchaDto;
import com.ganesh.api.service.CaptchaService;

@RestController
@RequestMapping("/api/captcha")
public class CaptchaController {
    @Autowired
    private CaptchaService captchaService;

    @RequestMapping("")
    public CaptchaDto getNewCaptcha() {
        return captchaService.createCaptcha();
    }
}
