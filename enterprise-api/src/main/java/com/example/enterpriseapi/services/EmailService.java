package com.example.enterpriseapi.services;

import com.example.enterpriseapi.domain.dtos.EmailDto;
import com.example.enterpriseapi.domain.entities.EmailEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "email-service", url = "${feign.client.config.email-service.url}/email")
public interface EmailService {
    @PostMapping("/sending-email")
    ResponseEntity<EmailEntity> sendingEmail(EmailDto emailDto);
}