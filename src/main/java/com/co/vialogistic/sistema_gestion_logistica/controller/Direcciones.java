package com.co.vialogistic.sistema_gestion_logistica.controller;


import com.co.vialogistic.sistema_gestion_logistica.dto.actualizaciones.ModificarDireccionDto;
import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.RespuestaDireccionDto;
import com.co.vialogistic.sistema_gestion_logistica.inferfaces.mapeadores.ModificarDireccionMapper;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Direccion;
import com.co.vialogistic.sistema_gestion_logistica.service.ModificarDirecciones;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class Direcciones {

    private final ModificarDirecciones modificarDirecciones;
    private final ModificarDireccionMapper modificarDireccionMapper;

    public Direcciones(ModificarDirecciones modificarDirecciones, ModificarDireccionMapper modificarDireccionMapper) {
        this.modificarDirecciones = modificarDirecciones;
        this.modificarDireccionMapper = modificarDireccionMapper;
    }

    @PatchMapping("direcciones/modificar/{id}")
    public ResponseEntity<RespuestaDireccionDto> modificarDirecciones(@Valid @RequestBody ModificarDireccionDto dto, @PathVariable @NotNull Long id){


        Direccion direccion = modificarDirecciones.responseModificarDirecciones(dto ,id);

        RespuestaDireccionDto response = modificarDireccionMapper.toDto(direccion);

        return ResponseEntity.status(200).body(response);

    }


}
