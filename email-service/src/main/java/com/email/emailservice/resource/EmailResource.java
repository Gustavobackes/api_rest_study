package com.email.emailservice.resource;

import com.email.emailservice.domain.dtos.EmailDto;
import com.email.emailservice.domain.entities.EmailEntity;
import com.email.emailservice.services.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("email")
public class EmailResource {

    private final EmailService emailService;

    public EmailResource(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/sending-email")
    public ResponseEntity<EmailEntity> sendingEmail(@RequestBody @Valid EmailDto emailDto) {
        EmailEntity emailEntity = new EmailEntity();
        BeanUtils.copyProperties(emailDto, emailEntity);
        EmailEntity sendEmailDto = emailService.sendEmail(emailEntity);
        return new ResponseEntity<>(sendEmailDto, HttpStatus.CREATED);
    }
}
