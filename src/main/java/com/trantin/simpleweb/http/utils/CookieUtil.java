package com.trantin.simpleweb.http.utils;

import javax.servlet.http.Cookie;
import java.rmi.server.UID;

public class CookieUtil {

    public static Cookie getRandomUserIdCookie(){
        return new Cookie("USERID", new UID().toString());
    }

}
