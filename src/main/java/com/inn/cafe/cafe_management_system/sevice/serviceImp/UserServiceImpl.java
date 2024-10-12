package com.inn.cafe.cafe_management_system.sevice.serviceImp;

import com.inn.cafe.cafe_management_system.content.CafeContent;
import com.inn.cafe.cafe_management_system.model.User;
import com.inn.cafe.cafe_management_system.repository.UserRepository;
import com.inn.cafe.cafe_management_system.sevice.UserService;
import com.inn.cafe.cafe_management_system.util.CafeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public ResponseEntity<String> signup(Map<String, String> requestMap) {
        //@slf4j ajoute des logger()
        log.info("Inside signup {}", requestMap);
        try{

        if (validateSignupMap(requestMap)){
            User user = userRepository.findAllByEmailId(requestMap.get("email"));
            if (Objects.isNull(user)) {
                 userRepository.save(getUserFromMap(requestMap));
                 return CafeUtils.getResponseEntity("Successfuly Register",HttpStatus.OK);
            }else {
                return CafeUtils.getResponseEntity("Email already exist", HttpStatus.BAD_REQUEST);
            }
        }else {
            return CafeUtils.getResponseEntity(CafeContent.INVALID_DATA, HttpStatus.BAD_REQUEST);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeContent.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private boolean validateSignupMap(Map<String, String> requestMap){
          if (requestMap.containsKey("name") && requestMap.containsKey("contacNumber")
                  && requestMap.containsKey("email") && requestMap.containsKey("password")){
              return true;
          }else return false;
    }

    private  User getUserFromMap(Map<String, String> requestMap){
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contacNumber"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("false");
        user.setRole("user");

        return user;
    }
}
