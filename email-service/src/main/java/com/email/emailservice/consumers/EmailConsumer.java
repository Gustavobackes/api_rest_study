package com.email.emailservice.consumers;


import com.email.emailservice.domain.dtos.EmailDto;
import com.email.emailservice.domain.entities.EmailEntity;
import com.email.emailservice.services.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class EmailConsumer {
    private final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailDto emailDto) {
        EmailEntity emailEntity = new EmailEntity();
        BeanUtils.copyProperties(emailDto, emailEntity);
        EmailEntity emailEntityResponse = emailService.sendEmail(emailEntity);
        log.info("Email Status: {}", Objects.requireNonNull(emailEntityResponse.getStatusEmail().toString()));
    }
}
