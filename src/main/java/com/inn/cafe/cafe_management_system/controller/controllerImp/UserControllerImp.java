package com.inn.cafe.cafe_management_system.controller.controllerImp;

import com.inn.cafe.cafe_management_system.content.CafeContent;
import com.inn.cafe.cafe_management_system.controller.UserController;
import com.inn.cafe.cafe_management_system.sevice.UserService;
import com.inn.cafe.cafe_management_system.util.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserControllerImp implements UserController {

   @Autowired
    UserService userService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            return userService.signup(requestMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    return CafeUtils.getResponseEntity(CafeContent.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
