package com.camiones.dto;

import com.camiones.entity.Rol;

public record LoginResponse(
        String mensaje,
        String username,
        Rol rol) {
}
