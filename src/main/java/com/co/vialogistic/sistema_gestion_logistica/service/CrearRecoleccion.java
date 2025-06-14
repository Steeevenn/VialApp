package com.co.vialogistic.sistema_gestion_logistica.service;

import com.co.vialogistic.sistema_gestion_logistica.controller.Usuarios;
import com.co.vialogistic.sistema_gestion_logistica.dto.CrearRecoleccionDto;
import com.co.vialogistic.sistema_gestion_logistica.inferfaces.creacionales.CreacionDeRecoleccion;
import com.co.vialogistic.sistema_gestion_logistica.inferfaces.mapeadores.RecoleccionMapper;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Direccion;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Recoleccion;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Usuario;
import com.co.vialogistic.sistema_gestion_logistica.model.enums.RolNombre;
import com.co.vialogistic.sistema_gestion_logistica.repository.DireccionesRepository;
import com.co.vialogistic.sistema_gestion_logistica.repository.RecoleccionRepository;
import com.co.vialogistic.sistema_gestion_logistica.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CrearRecoleccion implements CreacionDeRecoleccion {

    private final UsuarioRepository usuarioRepository;
    private final RecoleccionRepository recoleccionRepository;
    private final RecoleccionMapper recoleccionMapper;
    private final CrearOReutilizarDirecciones crearOReutilizarDirecciones;
    private final DireccionesRepository direccionesRepository;

    public CrearRecoleccion(UsuarioRepository usuarioRepository,
                            RecoleccionRepository recoleccionRepository,
                            RecoleccionMapper recoleccionMapper,
                            Usuarios usuarios, CrearOReutilizarDirecciones crearOReutilizarDirecciones, DireccionesRepository direccionesRepository) {
        this.usuarioRepository = usuarioRepository;
        this.recoleccionRepository = recoleccionRepository;
        this.recoleccionMapper = recoleccionMapper;
        this.crearOReutilizarDirecciones = crearOReutilizarDirecciones;
        this.direccionesRepository = direccionesRepository;
    }



    @Override
    @Transactional
    public Recoleccion crearRecoleccion(CrearRecoleccionDto crearRecoleccionDto) {

        //Convertir Dtos a entidades tipo direccion
        Direccion direccionRemitente = crearOReutilizarDirecciones.crearOReutilizarDirecciones(crearRecoleccionDto.direccionRemitente());
        Direccion direccionDestinatario = crearOReutilizarDirecciones.crearOReutilizarDirecciones(crearRecoleccionDto.direccionDestinatario());
        direccionRemitente = direccionesRepository.save(direccionRemitente);
        direccionDestinatario = direccionesRepository.save(direccionDestinatario);


        //Provisional mientras se impelenta spring security para traer el usaruioo del contexto de seguridad
        Long idUsuarioAgendo = crearRecoleccionDto.UsuarioAgendoId();

        Usuario administrador = usuarioRepository.findById(idUsuarioAgendo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        boolean esAdmin =  administrador.getRoles().stream()
                .anyMatch(rol -> rol.getNombre().equals(RolNombre.ADMINISTRADOR));

        if (!esAdmin){
            throw new RuntimeException("El usuairio no es administrador");
        }

        Recoleccion recoleccion = recoleccionMapper.toEntity(crearRecoleccionDto);

        recoleccion.setDireccionRemitente(direccionRemitente);
        recoleccion.setAdministradorAgendo(administrador);
        recoleccion.setNombreRemitente(crearRecoleccionDto.nombreRemitente());
        recoleccion.setTelefonoRemitente(crearRecoleccionDto.telefonoRemitente());
        recoleccion.setEmailRemitente(crearRecoleccionDto.emailRemitente());

        recoleccion.setNombreDestinatario(crearRecoleccionDto.nombreDestinatario());
        recoleccion.setTelefonoDestinatario(crearRecoleccionDto.telefonoDestinatario());
        recoleccion.setDireccionDestinatario(direccionDestinatario);

        recoleccion.setFechaHoraProgramadaRecoleccion(crearRecoleccionDto.fechaHoraProgramadaRecoleccion());
        recoleccion.setDescripcionPaquete(crearRecoleccionDto.descripcionPaquete());
        recoleccion.setPesoKg(crearRecoleccionDto.pesoKg());
        recoleccion.setAltoCm(crearRecoleccionDto.altoCm());
        recoleccion.setAnchoCm(crearRecoleccionDto.anchoCm());
        recoleccion.setLargoCm(crearRecoleccionDto.largoCm());
        recoleccion.setNotasAdministrador(crearRecoleccionDto.notasAdministrador());

        //Guardar recoleccion en la bd
        System.out.println("Datos guardado en la base de datos campo reolecciones: " + recoleccion  );
        recoleccionRepository.save(recoleccion);

    return recoleccion;
    }
}
