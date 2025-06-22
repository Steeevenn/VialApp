package com.co.vialogistic.sistema_gestion_logistica.dto.creacionales;

import com.co.vialogistic.sistema_gestion_logistica.model.enums.RolNombre;
import jakarta.validation.constraints.*;
import java.util.*;

public record CrearUsuarioDto(
        @NotBlank String username,
        @NotBlank String email,
        @NotBlank String password,
        String nombreCompleto,
        String telefono,
        Set<RolNombre> roles
) {


}

