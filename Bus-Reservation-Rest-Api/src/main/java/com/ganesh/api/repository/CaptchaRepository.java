package com.ganesh.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ganesh.api.entity.Captcha;

@Repository
public interface CaptchaRepository extends JpaRepository<Captcha, Long> {
}
