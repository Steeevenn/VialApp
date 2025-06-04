package com.co.vialogistic.sistema_gestion_logistica.model.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.util.*;

@Getter
public record CrearUsuarioDto(
        @NotBlank String username,
        @NotBlank String email,
        @NotBlank String password,
        String nombreCompleto,
        String telefono,
        Set<String> roles
) {}

