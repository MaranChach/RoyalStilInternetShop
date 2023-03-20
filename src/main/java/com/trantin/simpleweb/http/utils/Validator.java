package com.trantin.simpleweb.http.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static String trimImageUrl(String url){
        Pattern pattern = Pattern.compile("img].*\\[/img");

        Matcher matcher = pattern.matcher(url);

        if(!matcher.find())
            return url;

        String res = matcher.group();

        return res.substring(4, res.length() - 5);
    }
}
