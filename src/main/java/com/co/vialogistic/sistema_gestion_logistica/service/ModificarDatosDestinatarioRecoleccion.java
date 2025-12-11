package com.co.vialogistic.sistema_gestion_logistica.service;

import com.co.vialogistic.sistema_gestion_logistica.dto.actualizaciones.ModificarDatosDestinatarioRecoleccionDto;
import com.co.vialogistic.sistema_gestion_logistica.inferfaces.mapeadores.ModificacionDatosDestinatario;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Recoleccion;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Rol;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Usuario;
import com.co.vialogistic.sistema_gestion_logistica.model.enums.RolNombre;
import com.co.vialogistic.sistema_gestion_logistica.repository.DireccionesRepository;
import com.co.vialogistic.sistema_gestion_logistica.repository.RecoleccionRepository;
import com.co.vialogistic.sistema_gestion_logistica.repository.RoleRepository;
import com.co.vialogistic.sistema_gestion_logistica.repository.UsuarioRepository;
import com.co.vialogistic.sistema_gestion_logistica.service.validadores.UsuarioValidador;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ModificarDatosDestinatarioRecoleccion {


    private final RecoleccionRepository recoleccionRepository;
    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final DireccionesRepository direccionesRepository;
    private final UsuarioValidador usuarioValidador;
    private final ModificacionDatosDestinatario modificacionDatosDestinatario;


    public ModificarDatosDestinatarioRecoleccion(RecoleccionRepository recoleccionRepository, UsuarioRepository usuarioRepository, RoleRepository roleRepository, DireccionesRepository direccionesRepository, UsuarioValidador usuarioValidador, ModificacionDatosDestinatario modificacionDatosDestinatario) {
        this.recoleccionRepository = recoleccionRepository;
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.direccionesRepository = direccionesRepository;
        this.usuarioValidador = usuarioValidador;
        this.modificacionDatosDestinatario = modificacionDatosDestinatario;
    }


    @Transactional
    public Recoleccion modificarRecoleccionCamposDestinatario(
            ModificarDatosDestinatarioRecoleccionDto dto, Long recoleccionId) {

        // 1. Validar que el usuario exista y traerlo
        Usuario admin = usuarioValidador.obtenerOrThrow(dto.UsuarioAgendoId());

        System.out.println("Usuario que modifica encontrado: " + admin.getEmail());

        // 2. Validar que tenga rol ADMINISTRADOR

        boolean esAdmin = admin.getRoles().stream()
               .map(Rol::getNombre)
               .anyMatch(rol -> rol == (RolNombre.ADMINISTRADOR));

        if (!esAdmin) {
            throw new RuntimeException("El usuario no tiene permisos para modificar esta recolección");
        }

        // 3. Buscar la recolección a modificar
        Recoleccion recoleccion = recoleccionRepository.findById(recoleccionId)
                .orElseThrow(() -> new RuntimeException("La recolección no existe"));

        // 4. Actualizar la entidad existente usando el mapper
        modificacionDatosDestinatario.updateFromDto(dto, recoleccion);

        //5. Setear manualmente campo de domiciliario
        Usuario domiciliario = usuarioValidador.obtenerOrThrow(dto.domiciliarioAsignado());

        recoleccion.setDomiciliarioAsginado(domiciliario);

        // 6. Guardar cambios
        return recoleccionRepository.save(recoleccion);
    }

}
