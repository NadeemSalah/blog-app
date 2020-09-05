package com.blog.app.service;

import com.blog.app.repository.UserRepository;
import com.blog.app.repository.model.ConfirmationToken;
import com.blog.app.repository.model.User;
import com.blog.app.service.mappers.UserMapper;
import com.blog.app.service.request.SignUpUserRequest;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailService emailService;
    private final UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(@NonNull final String email) throws UsernameNotFoundException {
        final Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }

        final String errorMessage = MessageFormat.format("User with email {0} cannot be found.", email);
        throw new UsernameNotFoundException(errorMessage);
    }

    public void signUpUser(@NonNull final SignUpUserRequest request) {
        final User user = mapper.mapFromSignUpUserRequestToUser(request);
        log.info("start sign up user : [{}]", user);
        final String encryptedPassword = this.bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        final User createdUser = this.userRepository.save(user);
        final ConfirmationToken confirmationToken = new ConfirmationToken(createdUser);
        this.confirmationTokenService.saveConfirmationToken(confirmationToken);
        sendConfirmationMail(user.getEmail(), confirmationToken.getConfirmationToken());
    }

    public void confirmUser(@NonNull final ConfirmationToken confirmationToken) {
        log.info("start confirmUser with confirmation token : [{}]", confirmationToken);
        final User user = confirmationToken.getUser();
        user.setEnabled(true);
        this.userRepository.save(user);
        confirmationTokenService.deleteConfirmationToken(confirmationToken.getId());
    }

    public void sendConfirmationMail(@NonNull final String userMail, @NonNull final String token) {
        final SimpleMailMessage mailMessage = new SimpleMailMessage();
        final String userActivationLink = "http://localhost:8080/sign-up/confirm?token=";
        mailMessage.setTo(userMail);
        mailMessage.setFrom("<Mail>");
        mailMessage.setSubject("Mail Confirmation Link!");
        mailMessage.setText("Thank you for registering. Please click on the below link to activate your account." + userActivationLink + token);
        this.emailService.sendEmail(mailMessage);
    }
}
