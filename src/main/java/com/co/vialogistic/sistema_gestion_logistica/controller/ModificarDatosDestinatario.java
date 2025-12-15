    package com.co.vialogistic.sistema_gestion_logistica.controller;

    import com.co.vialogistic.sistema_gestion_logistica.dto.actualizaciones.ModificarDatosDestinatarioRecoleccionDto;
    import com.co.vialogistic.sistema_gestion_logistica.inferfaces.mapeadores.ModificacionDatosDestinatarioMapper;
    import com.co.vialogistic.sistema_gestion_logistica.model.entity.Recoleccion;
    import com.co.vialogistic.sistema_gestion_logistica.service.ModificarDatosDestinatarioRecoleccion;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;


    @RestController
    @RequestMapping("/api/v1")
    public class ModificarDatosDestinatario {


     private final ModificarDatosDestinatarioRecoleccion modificarDatosDestinatario;
     private final ModificacionDatosDestinatarioMapper modificacionDatosDestinatarioMapper;

        public ModificarDatosDestinatario(ModificarDatosDestinatarioRecoleccion modificarDatosDestinatario, ModificacionDatosDestinatarioMapper modificacionDatosDestinatarioMapper) {
            this.modificarDatosDestinatario = modificarDatosDestinatario;
            this.modificacionDatosDestinatarioMapper = modificacionDatosDestinatarioMapper;
        }


        // Pendiente gestionar el controlador utilizando el servicio ModificarDatosDestinatarioReoleccion


        @PutMapping("/recolecciones/{recoleccionId}/modificar/destinatario")
        public ResponseEntity<ModificarDatosDestinatarioRecoleccionDto> modificarRecoleccion(@RequestBody ModificarDatosDestinatarioRecoleccionDto dto, @PathVariable Long recoleccionId){


            Recoleccion recoleccionModificada = modificarDatosDestinatario.modificarRecoleccionCamposDestinatario(dto ,recoleccionId);

            // 2. Mapear la entidad final a DTO para la respuesta
            ModificarDatosDestinatarioRecoleccionDto modificacionRealizada =
                    modificacionDatosDestinatarioMapper.toEntityRecoleccionDto(recoleccionModificada);

            return ResponseEntity.ok(modificacionRealizada);
        }
    }
