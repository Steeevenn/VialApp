package com.co.vialogistic.sistema_gestion_logistica.service;

import com.co.vialogistic.sistema_gestion_logistica.controller.Usuarios;
import com.co.vialogistic.sistema_gestion_logistica.dto.creacionales.CrearRecoleccionDto;
import com.co.vialogistic.sistema_gestion_logistica.dto.creacionales.DireccionDto;
import com.co.vialogistic.sistema_gestion_logistica.exception.usuario.UsuarioNotAdminException;
import com.co.vialogistic.sistema_gestion_logistica.exception.usuario.UsuarioNotFoundException;
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


 private boolean mismaDireccion(Direccion d, DireccionDto dto){

        return d.getTipoVia() == dto.tipoVia()
                && iguales(d.getNumeroViaPrincipal(), dto.numeroViaPrincipal())
                && iguales(d.getNumeroPlaca(), dto.numeroPlaca())
                && iguales(d.getComplemento(), dto.complemento())
                && iguales(d.getBarrio(), dto.barrio())
                && iguales(d.getCodigoPostal(), dto.codigoPostal())
                && iguales(d.getCiudad(), dto.ciudad())
                && iguales(d.getReferenciasAdicionales(), dto.referenciasAdicionales());
 }


 private boolean iguales(String a, String b){

        if(a == null && b == null) return true;
        if(a == null || b == null) return false;

        return a.trim().equals(b.trim());
 }

    @Override
    @Transactional
    public Recoleccion crearRecoleccion(CrearRecoleccionDto crearRecoleccionDto) {

        //Conversor de objetos dto a entidad DIRECCION con validacion interna de duplicado de direcciones

        Direccion direccionRemitente = crearOReutilizarDirecciones.crearOReutilizarDirecciones(crearRecoleccionDto.direccionRemitente());
        Direccion direccionDestinatario = crearOReutilizarDirecciones.crearOReutilizarDirecciones(crearRecoleccionDto.direccionDestinatario());


        //Provisional mientras se impelenta spring security para traer el usaruioo del contexto de seguridad
        Long idUsuarioAgendo = crearRecoleccionDto.UsuarioAgendoId();

        Usuario administrador = usuarioRepository.findById(idUsuarioAgendo)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));

        boolean esAdmin =  administrador.getRoles().stream()
                .anyMatch(rol -> rol.getNombre().equals(RolNombre.ADMINISTRADOR));

        if (!esAdmin){
            throw new UsuarioNotAdminException("El usuairio no es administrador");
        }

        Recoleccion recoleccion = recoleccionMapper.toEntity(crearRecoleccionDto);

        recoleccion.setDireccionRemitente(direccionRemitente);
        //validacion de igualdad
        boolean remitenteIgual = mismaDireccion(direccionRemitente, crearRecoleccionDto.direccionRemitente());
        System.out.println("¿Remitente misma dirección que DTO? " + remitenteIgual);

        recoleccion.setUsuarioAgendoId(administrador);
        recoleccion.setNombreRemitente(crearRecoleccionDto.nombreRemitente());
        recoleccion.setTelefonoRemitente(crearRecoleccionDto.telefonoRemitente());
        recoleccion.setEmailRemitente(crearRecoleccionDto.emailRemitente());

        recoleccion.setNombreDestinatario(crearRecoleccionDto.nombreDestinatario());
        recoleccion.setTelefonoDestinatario(crearRecoleccionDto.telefonoDestinatario());
        recoleccion.setDireccionDestinatario(direccionDestinatario);

        boolean destinatarioIgual = mismaDireccion(direccionDestinatario, crearRecoleccionDto.direccionDestinatario());
        System.out.println("¿Destinatario misma dirección que DTO? " + destinatarioIgual);

        recoleccion.setFechaHoraProgramadaRecoleccion(crearRecoleccionDto.fechaHoraProgramadaRecoleccion());
        recoleccion.setDescripcionPaquete(crearRecoleccionDto.descripcionPaquete());
        recoleccion.setPesoKg(crearRecoleccionDto.pesoKg());
        recoleccion.setAltoCm(crearRecoleccionDto.altoCm());
        recoleccion.setAnchoCm(crearRecoleccionDto.anchoCm());
        recoleccion.setLargoCm(crearRecoleccionDto.largoCm());
        recoleccion.setNotasAdministrador(crearRecoleccionDto.notasAdministrador());

        recoleccionRepository.save(recoleccion);

        //Guardar recoleccion en la bd

    return recoleccion;
    }
}
