package com.co.vialogistic.sistema_gestion_logistica.service;

import com.co.vialogistic.sistema_gestion_logistica.exception.usuario.UsuarioNotFoundException;
import com.co.vialogistic.sistema_gestion_logistica.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class EliminarUsuarios {

    private final UsuarioRepository usuarioRepository;

    public EliminarUsuarios(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void eliminarUsuarios(Long usuarioById) {

        if(usuarioRepository.existsById(usuarioById)){
            usuarioRepository.deleteById(usuarioById);

        }else{

            throw new UsuarioNotFoundException(usuarioById);
        }
    }
}
