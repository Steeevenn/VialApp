    package com.co.vialogistic.sistema_gestion_logistica.service;

    import com.co.vialogistic.sistema_gestion_logistica.dto.creacionales.CrearHistorialEstadoDto;
    import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.RespuestaHistorialEstadoRecoleccionDto;
    import com.co.vialogistic.sistema_gestion_logistica.exception.recolecciones.RecoleccionNotExistException;
    import com.co.vialogistic.sistema_gestion_logistica.exception.usuario.UsuarioNotFoundException;
    import com.co.vialogistic.sistema_gestion_logistica.model.entity.HistorialEstadoRecoleccion;
    import com.co.vialogistic.sistema_gestion_logistica.model.entity.Recoleccion;
    import com.co.vialogistic.sistema_gestion_logistica.model.entity.Usuario;
    import com.co.vialogistic.sistema_gestion_logistica.model.enums.EstadoRecoleccion;
    import com.co.vialogistic.sistema_gestion_logistica.repository.HistorialEstadoRecoleccionRepository;
    import com.co.vialogistic.sistema_gestion_logistica.repository.RecoleccionRepository;
    import com.co.vialogistic.sistema_gestion_logistica.repository.UsuarioRepository;
    import jakarta.transaction.Transactional;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Optional;
    import java.util.stream.Collectors;

    @Service
    public class HistorialEstadoService {

        private final HistorialEstadoRecoleccionRepository historialRepository;
        private final RecoleccionRepository recoleccionRepository;
        private final UsuarioRepository usuarioRepository;

        public HistorialEstadoService(HistorialEstadoRecoleccionRepository historialRepository,
                                      RecoleccionRepository recoleccionRepository,
                                      UsuarioRepository usuarioRepository) {
            this.historialRepository = historialRepository;
            this.recoleccionRepository = recoleccionRepository;
            this.usuarioRepository = usuarioRepository;
        }



        @Transactional
        public void eliminarEstadoRecoleccion( Long recoleccionId, Long estadoId){

            HistorialEstadoRecoleccion historial = historialRepository
                    .findByIdAndRecoleccionId(recoleccionId, estadoId)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "No existe historial con ese id para la recolección dada"));

            historialRepository.delete(historial);
        }


        public RespuestaHistorialEstadoRecoleccionDto creacionHistorialEstado(CrearHistorialEstadoDto dto){

            Recoleccion recoleccion = recoleccionRepository.findById(dto.recoleccionId()).
                orElseThrow( () -> new RecoleccionNotExistException("No se encontro el recoleccion con el id proporcionado"));


             EstadoRecoleccion  dtoEstadoAnterior = dto.estadoAnterior();
             EstadoRecoleccion actual = recoleccion.getEstadoRecoleccion();
             EstadoRecoleccion dtoEstadoNuevo= dto.estadoNuevo();

             //Validacion inicial para que conicida lo que viene del dto a lo que hay en la base de datos
          //   if(actual != dtoEstadoAnterior){
            //     throw new IllegalArgumentException("ESTADO ANTERIOR NO COINCIDE CON EL ESTADO ACTUAL= " + actual + " DTO= " + dtoEstadoAnterior);
             //}

             //Validacion desde el service para cumplir regla de negocio trancisiones validad
             if(!actual.puedeCambiarEstadoRecoleccionSi(dtoEstadoNuevo)){
                 throw new IllegalArgumentException("TRANCISION NO PERMITIDA ACTUAL= " + actual + " NUEVO = " + dtoEstadoAnterior);

             }

            // Validacion para se ponga motivo cambio obligatorio
            if (dtoEstadoNuevo == EstadoRecoleccion.INCIDENCIA_RECOLECCION &&
                    (dto.motivoCambio() == null || dto.motivoCambio().isBlank())) {
                throw new IllegalArgumentException("Debe indicar un motivo para la incidencia.");
            }


            Usuario usuario = null;

            if(dto.cambiadoPorUsuarioId() != null ){
                usuario = usuarioRepository.findById(dto.cambiadoPorUsuarioId())
                        .orElseThrow(() -> new UsuarioNotFoundException("No se encontro el usuario con el id proporcinado"));
            }


            HistorialEstadoRecoleccion historialEstadoRecoleccion = new HistorialEstadoRecoleccion();

            historialEstadoRecoleccion.setRecoleccion(recoleccion);
            historialEstadoRecoleccion.setEstadoAnterior(recoleccion.getEstadoRecoleccion());
            historialEstadoRecoleccion.setEstadoNuevo(dto.estadoNuevo());

            historialEstadoRecoleccion.setCambiadoPorUsuario(usuario);
            historialEstadoRecoleccion.setMotivoCambio(dto.motivoCambio());


            // SETEAR DATOS DE ENTIDDAD RECOLECCION PARA EL CAMPO DE ESTADO NUEVO UNICAMENTE
            recoleccion.setEstadoRecoleccion(dto.estadoNuevo());

            //Guardar en el repository
            HistorialEstadoRecoleccion saved = historialRepository.save(historialEstadoRecoleccion);



            return toResponseDto(saved);

        }



        public List<RespuestaHistorialEstadoRecoleccionDto> responseHistorialEstado(Long recoleccionId){

            return  historialRepository.findByRecoleccionIdOrderByTimestampCambioDesc(recoleccionId)
                    .stream()
                    .map(this::toResponseDto)
                    .collect(Collectors.toList());

        }



        private RespuestaHistorialEstadoRecoleccionDto toResponseDto(HistorialEstadoRecoleccion historial) {
            return new RespuestaHistorialEstadoRecoleccionDto(
                    historial.getId(),
                    historial.getRecoleccion().getId(),
                    historial.getEstadoAnterior(),
                    historial.getEstadoNuevo(),
                    historial.getCambiadoPorUsuario() != null ? historial.getCambiadoPorUsuario().getId() : null,
                    historial.getMotivoCambio(),
                    historial.getTimestampCambio()
            );
        }
    }
