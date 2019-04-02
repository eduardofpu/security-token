package com.security.config;

import java.util.concurrent.TimeUnit;

public class SecurityConstants {

    //Authorization Bearer uheauhehgy3u231uh
    static final  String SECRET = "DevDojoFoda";
    static final  String TOKEN_PREFIX = "Bearer";
    static final  String HEADER_STRING = "Authorization";
    static final  String SIGN_UP_URL = "/users/sign-up";
    static final long EXPIRATION_TIME = 86400000L; // expira em um dia


//    public static void main(String[] args) {
//        //Converter milli segundos em um dia
//        System.out.println(TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
//    }
}
