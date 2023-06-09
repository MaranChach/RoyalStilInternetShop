package com.trantin.simpleweb.http.utils;

import java.util.Random;

public class PasswordUtil {

    public static String generatePassword(int length){

        String possibles = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890!?.,:;_";
        StringBuilder passBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            passBuilder.append(possibles.charAt(random.nextInt(possibles.length()-1)));
        }
        return passBuilder.toString();
    }

}
