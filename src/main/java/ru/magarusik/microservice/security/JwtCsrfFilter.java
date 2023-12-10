package ru.magarusik.microservice.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@AllArgsConstructor
public class JwtCsrfFilter extends OncePerRequestFilter {
    private final CsrfTokenRepository tokenRepository;
    private final HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        var csrfToken = loadToken(request, response);
        csrfToken = generateNewTokenIfMissingAndSaveToRepository(csrfToken, request, response);

        request.setAttribute(CsrfToken.class.getName(), csrfToken);
        request.setAttribute(csrfToken.getParameterName(), csrfToken);

        if ("/auth/login".equals(request.getServletPath())) {
            try {
                filterChain.doFilter(request, response);
            } catch (Exception e) {
                resolver.resolveException(request,
                        response,
                        null,
                        new MissingCsrfTokenException(csrfToken.getToken())
                );
            }
        } else {
            String actualToken = request.getHeader(csrfToken.getHeaderName());
            if (actualToken == null) {
                actualToken = request.getParameter(csrfToken.getParameterName());
            }
            try {
                if (!StringUtils.isEmpty(actualToken)) {
                    Jwts.parser()
                            .setSigningKey(((JwtTokenRepository) tokenRepository).getSecret())
                            .parseClaimsJws(actualToken);

                    filterChain.doFilter(request, response);
                } else
                    resolver.resolveException(request, response, null, new InvalidCsrfTokenException(csrfToken, actualToken));
            } catch (JwtException e) {
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("Invalid CSRF token found for " + UrlUtils.buildFullRequestUrl(request));
                }
                resolver.resolveException(request, response, null, new InvalidCsrfTokenException(csrfToken, actualToken));
            }
        }
    }

    private CsrfToken generateNewTokenIfMissingAndSaveToRepository(
            CsrfToken csrfToken,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        var token = csrfToken == null ? this.tokenRepository.generateToken(request) : csrfToken;
        this.tokenRepository.saveToken(csrfToken, request, response);
        return token;
    }

    private CsrfToken loadToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        request.setAttribute(HttpServletResponse.class.getName(), response);
        return this.tokenRepository.loadToken(request);
    }
}