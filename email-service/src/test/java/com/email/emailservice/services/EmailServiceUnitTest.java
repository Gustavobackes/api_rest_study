package com.email.emailservice.services;

import com.email.emailservice.domain.dtos.EmailDto;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EmailServiceUnitTest {


    @Test
    void should() {
        EmailDto emailDto = EmailDto.builder()
                .ownerRef("Gustavo")
                .emailFrom("gustavo.kitzberger@softplan.com.br")
                .emailTo("kitzgustavo@gmail.com")
                .subject("Example subject")
                .text("Example text")
                .build();

        Gson gson = new Gson();
        String json = gson.toJson(emailDto);
        Assertions.assertNotNull(json);
    }
}