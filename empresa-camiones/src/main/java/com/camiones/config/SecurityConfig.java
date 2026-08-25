package com.camiones.config;

import com.camiones.entity.Usuario;
import com.camiones.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(UsuarioRepository usuarioRepository) {
        return username -> {
            Usuario usuario = usuarioRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

            return User.withUsername(usuario.getUsername())
                    .password("{noop}" + usuario.getPassword())
                    .roles(usuario.getRol().name())
                    .disabled(!usuario.isActivo())
                    .build();
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())

                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.sameOrigin()))

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/error", "/h2-console/**").permitAll()
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/supervisor/**").hasAnyRole("ADMIN", "SUPERVISOR")
                        .anyRequest().authenticated())

                .httpBasic(Customizer.withDefaults())

                .formLogin(form -> form
                        .defaultSuccessUrl("/api/supervisor/camiones", true));

        return http.build();
    }
}
