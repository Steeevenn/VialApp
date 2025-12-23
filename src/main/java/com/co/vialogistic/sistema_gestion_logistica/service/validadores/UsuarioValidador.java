package com.co.vialogistic.sistema_gestion_logistica.service.validadores;

import com.co.vialogistic.sistema_gestion_logistica.exception.recolecciones.AgendadorDeDomiciliarioException;
import com.co.vialogistic.sistema_gestion_logistica.exception.recolecciones.RolNotFoundException;
import com.co.vialogistic.sistema_gestion_logistica.exception.usuario.UsuarioNotFoundException;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Usuario;
import com.co.vialogistic.sistema_gestion_logistica.model.enums.RolNombre;
import com.co.vialogistic.sistema_gestion_logistica.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

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

    public Usuario validarRolDomiciliario(Long domiciliarioId){

        return   usuarioRepository.findById(domiciliarioId).stream()
                .filter(rol -> rol.getRoles().stream().anyMatch(rolName -> rolName.getNombre().equals(RolNombre.DOMICILIARIO )))
                .findFirst()
                .orElseThrow(()-> new AgendadorDeDomiciliarioException(domiciliarioId));

    }


}