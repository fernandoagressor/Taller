package com.camiones.dto;

import com.camiones.entity.Rol;

public record LoginResponse(
        String token,
        String username,
        Rol rol) {
}
