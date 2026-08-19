package com.camiones.dto;
import jakarta.validation.constraints.NotBlank;
public record ConductorRequest(@NotBlank String nombre,@NotBlank String documento,@NotBlank String licencia,boolean activo) { }
