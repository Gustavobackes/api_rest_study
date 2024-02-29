package com.email.emailservice.services.util;

import lombok.experimental.UtilityClass;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@UtilityClass
public class UserUtil {

    public static Session getUserSession(Properties props, String userName, String password) {
        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });
    }
}
