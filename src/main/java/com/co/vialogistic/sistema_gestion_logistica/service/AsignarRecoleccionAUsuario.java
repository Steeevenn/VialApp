package com.co.vialogistic.sistema_gestion_logistica.service;

import com.co.vialogistic.sistema_gestion_logistica.repository.RecoleccionRepository;
import com.co.vialogistic.sistema_gestion_logistica.repository.UsuarioRepository;

public class AsignarRecoleccionAUsuario {

    private final UsuarioRepository usuarioRepository;
    private final RecoleccionRepository recoleccionRepository;

    public AsignarRecoleccionAUsuario(UsuarioRepository usuarioRepository, RecoleccionRepository recoleccionRepository) {
        this.usuarioRepository = usuarioRepository;
        this.recoleccionRepository = recoleccionRepository;
    }





}
