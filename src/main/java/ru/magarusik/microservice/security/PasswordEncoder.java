package ru.magarusik.microservice.security;

import lombok.Getter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Getter
public final class PasswordEncoder {
    private final BCryptPasswordEncoder encoder;

    public PasswordEncoder() {
        encoder = new BCryptPasswordEncoder();
    }

    public String encode(CharSequence password) {
        return encoder.encode(password);
    }
}

