package com.camiones.service;

import com.camiones.dto.LoginRequest;
import com.camiones.dto.LoginResponse;
import com.camiones.entity.Usuario;
import com.camiones.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByUsername(request.username())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Usuario o contraseña incorrectos"));

        if (!usuario.isActivo() || !usuario.getPassword().equals(request.password())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Usuario o contraseña incorrectos");
        }

        return new LoginResponse(
                "Login correcto",
                usuario.getUsername(),
                usuario.getRol());
    }
}
