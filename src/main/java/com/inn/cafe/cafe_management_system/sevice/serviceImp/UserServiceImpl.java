package com.inn.cafe.cafe_management_system.sevice.serviceImp;

import com.inn.cafe.cafe_management_system.content.CafeContent;
import com.inn.cafe.cafe_management_system.dto.UserDto;
import com.inn.cafe.cafe_management_system.jwt.CustomerUserService;
import com.inn.cafe.cafe_management_system.jwt.JwtFilter;
import com.inn.cafe.cafe_management_system.jwt.JwtUtils;
import com.inn.cafe.cafe_management_system.model.User;
import com.inn.cafe.cafe_management_system.repository.UserRepository;
import com.inn.cafe.cafe_management_system.sevice.UserService;
import com.inn.cafe.cafe_management_system.util.CafeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomerUserService customerUserService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    JwtFilter jwtFilter;

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

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
       log.info("inside login");
       try {
           Authentication auth = authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
                           requestMap.get("email"),requestMap.get("password")
                   )
           );
           if (auth.isAuthenticated()){
               if (customerUserService.getUserDetail().getStatus().equalsIgnoreCase("true")){
                   return  new ResponseEntity<String>("{\"token\": \""+
                    jwtUtils.generateToken(customerUserService.getUserDetail().getEmail(),
                       customerUserService.getUserDetail().getRole()) + "\"}",
                           HttpStatus.OK  );

               }else {
                   return  new ResponseEntity<String>("{ \"message\":\""+"Wait for admin approval."+"\"}",
                           HttpStatus.BAD_REQUEST);
               }
           }
       } catch (Exception e) {
           log.error("{}",e);
       }
        return  new ResponseEntity<String>("{ \"message\":\""+"Bad Credentials"+"\"}",
                HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<UserDto>> getAllUser() {
        try {
           if (jwtFilter.isAdmin()){

           }else {
               return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
