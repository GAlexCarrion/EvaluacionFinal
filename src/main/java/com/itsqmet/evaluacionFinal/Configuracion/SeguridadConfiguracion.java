package com.itsqmet.evaluacionFinal.Configuracion;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SeguridadConfiguracion {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/login", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/docente/**").hasRole("DOCENTE")
                        .requestMatchers("/estudiante/**").hasRole("ESTUDIANTE")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                        .successHandler((request, response, authentication) -> {
                            String role = authentication.getAuthorities().toString();
                            if (role.contains("ROLE_ADMIN")) {
                                response.sendRedirect("/admin/dashboard");
                            } else if (role.contains("ROLE_DOCENTE")) {
                                response.sendRedirect("/docente/cursos");
                            } else if (role.contains("ROLE_ESTUDIANTE")) {
                                response.sendRedirect("/estudiante/miscursos");
                            }
                        })
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }
}