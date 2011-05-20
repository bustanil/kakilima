package org.inharmonia.kakilima.web.util;

import org.apache.wicket.RequestCycle;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebResponse;

import javax.servlet.http.Cookie;

public class CookiesUtil {
    private static final int COOKIE_TTL = 30; // minutes

    private CookiesUtil(){}

    public static String getCookieByName(String name){
        Cookie cookie = getRequest().getCookie(name);
        if(cookie != null){
            return cookie.getValue();
        }
        return null;
    }

    private static WebRequest getRequest() {
        return ((WebRequest) RequestCycle.get().getRequest());
    }

    public static void setCookie(String name, String value){
        setCookie(name, value, 30);
    }

    public static void setCookie(String name, String value, int expiryInMinutes){
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(expiryInMinutes * 60);
        getResponse().addCookie(cookie);
    }


    private static WebResponse getResponse() {
        return ((WebResponse) RequestCycle.get().getResponse());
    }

}
