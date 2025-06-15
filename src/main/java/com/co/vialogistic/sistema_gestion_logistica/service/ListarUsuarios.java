package com.co.vialogistic.sistema_gestion_logistica.service;

import com.co.vialogistic.sistema_gestion_logistica.dto.creacionales.CrearUsuarioDto;
import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.RespuestaListarUsuariosDto;
import com.co.vialogistic.sistema_gestion_logistica.inferfaces.mapeadores.UsuarioMapper;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Usuario;
import com.co.vialogistic.sistema_gestion_logistica.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ListarUsuarios implements com.co.vialogistic.sistema_gestion_logistica.inferfaces.ListarUsuarios {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;


    public ListarUsuarios(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public List<RespuestaListarUsuariosDto> obtenerTodosLosUsuarios() {

        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::usuarioToRespuestaListarDto)
                .toList();

    }
}
