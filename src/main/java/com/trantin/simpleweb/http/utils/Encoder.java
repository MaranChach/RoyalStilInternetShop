package com.trantin.simpleweb.http.utils;

import org.mindrot.jbcrypt.BCrypt;

public class Encoder {
    public static String encodePassword(String password) {
        // Генерация соли
        String salt = BCrypt.gensalt();

        // Хэширование пароля
        String hashedPassword = BCrypt.hashpw(password, salt);

        // Проверка пароля
        if (BCrypt.checkpw(password, hashedPassword)) {
            return hashedPassword;
        } else {
            return null;
        }
    }
}
