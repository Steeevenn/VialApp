package com.co.vialogistic.sistema_gestion_logistica.repository;

import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.ListarRecoleccionesPorUsuarioDto;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Recoleccion;
import com.co.vialogistic.sistema_gestion_logistica.model.enums.EstadoRecoleccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RecoleccionRepository extends JpaRepository<Recoleccion, Long> {

    // consulta JPQL explícita

    @Query("SELECT r FROM Recoleccion r WHERE r.usuarioAgendoId.id = :usuarioId")
    List<Recoleccion> listarRecoleccionesPorUsuario(Long usuarioId, EstadoRecoleccion estadoRecoleccion);

}
