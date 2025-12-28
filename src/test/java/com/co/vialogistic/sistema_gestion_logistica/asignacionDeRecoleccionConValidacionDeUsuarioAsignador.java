package com.co.vialogistic.sistema_gestion_logistica;


import com.co.vialogistic.sistema_gestion_logistica.exception.recolecciones.AgendadorDeDomiciliarioException;
import com.co.vialogistic.sistema_gestion_logistica.inferfaces.mapeadores.RecoleccionMapper;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Recoleccion;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Usuario;
import com.co.vialogistic.sistema_gestion_logistica.model.enums.EstadoRecoleccion;
import com.co.vialogistic.sistema_gestion_logistica.repository.RecoleccionRepository;
import com.co.vialogistic.sistema_gestion_logistica.repository.UsuarioRepository;
import com.co.vialogistic.sistema_gestion_logistica.service.GestionDeRecolecciones;
import com.co.vialogistic.sistema_gestion_logistica.service.validadores.UsuarioValidador;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class asignacionDeRecoleccionConValidacionDeUsuarioAsignador {


//Service - Implementacion real del service con @mocks en los campos del constructor
    @InjectMocks
    private GestionDeRecolecciones gestion;

    @Mock
    private UsuarioRepository userRepository;
    @Mock
    private RecoleccionRepository recoleccionRepository;
    @Mock
    private RecoleccionMapper recoleccionMapper;
    @Mock
    private UsuarioValidador usuarioValidador;


    @Test
    void asignarRecoleccionAUsuario_deberiaAsignarDomiciliarioYCambiarEstadoYGuardar() {

        // ARRANGE
        Long idUsuarioQueAsigno = 10L;
        Long idDomiciliario = 20L;
        Long idRecoleccion = 30L;

        Usuario usuarioDomiciliario = new Usuario();
        usuarioDomiciliario.setId(idDomiciliario);

        Recoleccion recoleccion = new Recoleccion();
        recoleccion.setId(idRecoleccion);
        recoleccion.setEstadoRecoleccion(EstadoRecoleccion.PENDIENTE_ASIGNACION);

        when(usuarioValidador.validarRolDomiciliario(idDomiciliario)).thenReturn(usuarioDomiciliario);

        when(recoleccionRepository.listarRecoleccionesPorUsuario(idUsuarioQueAsigno, EstadoRecoleccion.PENDIENTE_ASIGNACION)).thenReturn(List.of(recoleccion));

        // Act  hace referencia a invocar al servicio directamente como se inyecta por @InjectMocks crea una instancia real del servicio y pasa los parametros del contructor como @mocks
        gestion.AsignarRecoleccionAUsuario(idUsuarioQueAsigno, idDomiciliario, idRecoleccion);

        // Assert (Interacciones) verify recibe el mock no el resultado de la llamada

        verify(usuarioValidador).validarRolDomiciliario(idDomiciliario);
        verify(recoleccionRepository).listarRecoleccionesPorUsuario(idUsuarioQueAsigno, EstadoRecoleccion.PENDIENTE_ASIGNACION);

        // Service se guarda con saveAll si en algun momento cambia por save se debe modificar el test para que no quede fallido
        verify(recoleccionRepository).saveAll(argThat((ArgumentMatcher<List<Recoleccion>>) list ->
                list != null
                        && list.size() == 1
                        && list.getFirst() != null
                        && idRecoleccion.equals(list.getFirst().getId())
                        && list.getFirst().getEstadoRecoleccion() == EstadoRecoleccion.ASIGNADA
                        && list.getFirst().getDomiciliarioAsginado() != null && idDomiciliario.equals(list.getFirst().getDomiciliarioAsginado().getId())
        ));

        verifyNoMoreInteractions(usuarioValidador, recoleccionRepository);



    }
}
