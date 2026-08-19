package com.camiones.dto;
import jakarta.validation.constraints.NotBlank;
public record CamionRequest(@NotBlank String placa,@NotBlank String marca,@NotBlank String modelo,boolean activo) { }
