package com.inn.cafe.cafe_management_system.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CafeUtils {
    private  CafeUtils(){

    }
    //une response Entity ameliorer
    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus){
        return new ResponseEntity<String>("{\"message\": \""+responseMessage+"\"}", httpStatus);
    }
}
