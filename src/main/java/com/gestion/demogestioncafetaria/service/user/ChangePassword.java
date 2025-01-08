package com.gestion.demogestioncafetaria.service.user;

import com.gestion.demogestioncafetaria.entity.User;
import com.gestion.demogestioncafetaria.repository.UserRepository;
import com.gestion.demogestioncafetaria.resource.user.ChangePasswordRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChangePassword {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public String execute(ChangePasswordRequest request) throws BadRequestException {
        log.info("Inside ChangePassword : {}", request);
        User user = getAuthenticatedUser();
        validateOldPassword(request.oldPassword(), user.getPassword());
        updatePassword(user, request.newPassword());
        return "Password Updated Successfully!";
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication.getPrincipal() instanceof User)) {
            throw new IllegalStateException("Principal is not a valid user");
        }
        return (User) authentication.getPrincipal();
    }

    private void validateOldPassword(String oldPassword, String storedPassword) throws BadRequestException {
        if (!passwordEncoder.matches(oldPassword, storedPassword)) {
            throw new BadRequestException("Incorrect Old Password");
        }
    }

    private void updatePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

}
