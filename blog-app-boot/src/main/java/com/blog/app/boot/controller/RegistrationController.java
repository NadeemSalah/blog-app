package com.blog.app.boot.controller;

import com.blog.app.repository.model.ConfirmationToken;
import com.blog.app.service.ConfirmationTokenService;
import com.blog.app.service.UserService;
import com.blog.app.service.request.SignUpUserRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@AllArgsConstructor
@Slf4j
public class RegistrationController {

    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;

    @PostMapping("/sign-up")
    String signUp(@Valid final SignUpUserRequest user) {
        log.info("start sign up request [{}]", user);
        userService.signUpUser(user);
        return "redirect:/sign-in";
    }

    @GetMapping("/sign-up/confirm")
    String confirmMail(@RequestParam("token") String token) {
        log.info("start confirm user registration with token [{}]", token);
        Optional<ConfirmationToken> optionalConfirmationToken = confirmationTokenService.findConfirmationTokenByToken(token);
        optionalConfirmationToken.ifPresent(userService::confirmUser);
        return "redirect:/sign-in";
    }
}
