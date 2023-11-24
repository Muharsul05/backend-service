package ru.magarusik.microservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.HandlerExceptionResolver;
import ru.magarusik.microservice.service.UserService;

import java.util.List;

@Configuration
//@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenRepository jwtTokenRepository;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Autowired
    private PasswordEncoder encoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .cors(Customizer.withDefaults())
                .sessionManagement((session) ->
                        session.sessionCreationPolicy(SessionCreationPolicy.NEVER))
//                .addFilterAt(new JwtCsrfFilter(jwtTokenRepository, resolver), CsrfFilter.class)
//                .csrf(
//                        (x) -> x.ignoringRequestMatchers("/**")
//                )
                .authorizeHttpRequests(requests -> requests
                                .anyRequest().permitAll()
//                        .requestMatchers("/registration", "/auth/login").permitAll()
//                        .anyRequest().authenticated()
                )
                .logout(LogoutConfigurer::permitAll)
                .httpBasic(
                        (auth) -> auth
                                .authenticationEntryPoint(
                                        (request, response, e) -> resolver
                                                .resolveException(request, response, null, e)
                                )
                ).build();

    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        var auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(encoder.getEncoder());
        return auth;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        var configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:2121"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));;
        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

