package com.gestion.demogestioncafetaria.service.user;

import com.gestion.demogestioncafetaria.exception.ResourceNotFoundException;
import com.gestion.demogestioncafetaria.jwt.JwtFilter;
import com.gestion.demogestioncafetaria.notification.EmailService;
import com.gestion.demogestioncafetaria.repository.UserRepository;
import com.gestion.demogestioncafetaria.resource.user.UpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.gestion.demogestioncafetaria.constent.CafeConstent.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateUser {
    private final UserRepository userRepository;
    private final JwtFilter jwtFilter;
    private final GetAllUser getAllUser;
    private final EmailService emailService;


    public String execute(UpdateRequest request) {
        log.info("Inside UpdateUser {}", request);
        var user = this.userRepository.findById(request.id()).orElseThrow(
                () -> new ResourceNotFoundException(USER_NOT_FOUND)
        );
        user.setStatus(request.status());
        this.userRepository.save(user);
        this.sendMailToAllAdmin(request.status(), user.getEmail(), this.getAllUser.getAllAdmin());
        return "user status update Successfully !";
    }

    private void sendMailToAllAdmin(boolean status, String email, List<String> allAdmin) {
        boolean remove = allAdmin.remove(jwtFilter.getCurentEmail());
        if (status && remove) {
            this.emailService.sendSimpleMessage(jwtFilter.getCurentEmail(), "Account Approved", "USER:- " + email +
                    " \n is approved by \n ADMIN:- " + jwtFilter.getCurentEmail(), allAdmin);
        } else {
            this.emailService.sendSimpleMessage(jwtFilter.getCurentEmail(), "Account Desabled", "USER:- " + email +
                    " \n is desabled by \n ADMIN:- " + jwtFilter.getCurentEmail(), allAdmin);
        }
    }
}
