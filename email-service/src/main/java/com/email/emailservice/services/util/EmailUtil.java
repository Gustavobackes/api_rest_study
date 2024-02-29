package com.email.emailservice.services.util;

import lombok.experimental.UtilityClass;

import java.util.Properties;

@UtilityClass
public class EmailUtil {

    public static Properties getEmailProperties(String host, int port) {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", String.valueOf(port));
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        return props;
    }


}
