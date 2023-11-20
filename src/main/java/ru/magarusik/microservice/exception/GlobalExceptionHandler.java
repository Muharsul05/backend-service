package ru.magarusik.microservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.magarusik.microservice.security.JwtTokenRepository;

@RestControllerAdvice
@Getter
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private JwtTokenRepository tokenRepository;

    public GlobalExceptionHandler(JwtTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @ExceptionHandler({
            AuthenticationException.class,
            MissingCsrfTokenException.class,
            InvalidCsrfTokenException.class,
            SessionAuthenticationException.class
    })
    public ErrorInfo handleAuthenticationException(
            RuntimeException ex,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        this.tokenRepository.clearToken(response);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return new ErrorInfo(UrlUtils.buildFullRequestUrl(request), "error.authorization");
    }

    @Getter
    public class ErrorInfo {
        private final String url;
        private final String info;

        ErrorInfo(String url, String info) {
            this.url = url;
            this.info = info;
        }
    }
}