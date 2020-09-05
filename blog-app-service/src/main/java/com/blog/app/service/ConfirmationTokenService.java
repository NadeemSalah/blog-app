package com.blog.app.service;

import com.blog.app.repository.ConfirmationTokenRepository;
import com.blog.app.repository.model.ConfirmationToken;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    void saveConfirmationToken(@NonNull final ConfirmationToken confirmationToken) {
        log.info("start saveConfirmationToken with confirmationToken [{}]", confirmationToken);
        this.confirmationTokenRepository.save(confirmationToken);
    }

    public void deleteConfirmationToken(@NonNull final Long id) {
        this.confirmationTokenRepository.deleteById(id);
    }

    public Optional<ConfirmationToken> findConfirmationTokenByToken(String token) {
        log.info("start findConfirmationTokenByToken with token [{}]", token);
        return confirmationTokenRepository.findByConfirmationToken(token);
    }
}
