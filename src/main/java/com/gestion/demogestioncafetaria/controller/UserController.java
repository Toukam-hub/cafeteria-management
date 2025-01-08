package com.gestion.demogestioncafetaria.controller;

import com.gestion.demogestioncafetaria.resource.user.*;
import com.gestion.demogestioncafetaria.service.user.*;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    private final SignUp signUp;
    private final Login login;
    private final GetAllUser getAllUser;
    private final UpdateUser updateUser;
    private final ChangePassword changePassword;
    private final ForgotPassword forgotPassword;
    private final ResetPasswordService resetPasswordService;

    public UserController(SignUp signUp, Login login, GetAllUser getAllUser, UpdateUser updateUser, ChangePassword changePassword, ForgotPassword forgotPassword, ResetPasswordService resetPasswordService) {
        this.signUp = signUp;
        this.login = login;
        this.getAllUser = getAllUser;
        this.updateUser = updateUser;
        this.changePassword = changePassword;
        this.forgotPassword = forgotPassword;
        this.resetPasswordService = resetPasswordService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody @Valid SignUpRequest request) {
        this.signUp.execute(request);
        return ResponseEntity.ok("successful signup !");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(this.login.execute(request));
    }

    @GetMapping("/get")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserRecord>> getAllUser() {
        return ResponseEntity.ok(this.getAllUser.getAllUser());
    }

    @PatchMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateStatus(@RequestBody @Valid UpdateRequest request) {
        return ResponseEntity.ok(this.updateUser.execute(request));
    }

    @GetMapping("/checkToken")
    public ResponseEntity<String> checkToken() {
        return ResponseEntity.ok("checkToken");
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody @Valid ChangePasswordRequest request) throws BadRequestException {
        return ResponseEntity.ok(this.changePassword.execute(request));
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> request) {
        return ResponseEntity.ok(this.forgotPassword.execute(request));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestBody Map<String, String> request) {
        boolean isSuccess = resetPasswordService.execute(token, request);
        if (isSuccess) {
            return ResponseEntity.ok("Password has been successfully reset. !");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired token.");
        }
    }
}
