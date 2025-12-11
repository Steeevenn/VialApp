package com.co.vialogistic.sistema_gestion_logistica.service.validadores;

import com.co.vialogistic.sistema_gestion_logistica.exception.UsuarioNotFoundException;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Usuario;
import com.co.vialogistic.sistema_gestion_logistica.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

@Component
public class UsuarioValidador {

    private final UsuarioRepository usuarioRepository;
    public UsuarioValidador(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public Usuario obtenerOrThrow (Long usuarioId){

                 return usuarioRepository.findById(usuarioId)
                 .orElseThrow(() -> new UsuarioNotFoundException("Usuario con id:" + usuarioId + " No existe"));

    }


}