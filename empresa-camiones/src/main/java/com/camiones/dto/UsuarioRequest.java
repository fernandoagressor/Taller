package com.camiones.dto;
import com.camiones.entity.Rol;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public record UsuarioRequest(
        @NotBlank String username,
        @NotBlank String password,
        @NotNull Rol rol) {

}
