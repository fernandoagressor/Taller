package com.camiones.service;

import com.camiones.dto.UsuarioRequest;
import com.camiones.entity.Usuario;
import com.camiones.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repo;

    public List<Usuario> listar() {
        return repo.findAll();
    }

    public Usuario crear(UsuarioRequest r) {
        return repo.save(Usuario.builder()
                .username(r.username())
                .password(r.password())
                .rol(r.rol())
                .activo(true)
                .build());
    }

    public Usuario buscar(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public void eliminar(Long id) {
        repo.delete(buscar(id));
    }
}
