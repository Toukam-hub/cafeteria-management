package com.gestion.demogestioncafetaria.utils;

import java.util.Date;

public class CafeUtils {
    private CafeUtils(){}

    public  static String getUUid(){
        Date date = new Date();
        Long l = date.getTime();
        return l.toString() ;
    }
}
