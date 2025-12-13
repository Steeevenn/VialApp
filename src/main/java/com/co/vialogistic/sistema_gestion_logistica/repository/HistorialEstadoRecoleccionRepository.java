package com.co.vialogistic.sistema_gestion_logistica.repository;

import com.co.vialogistic.sistema_gestion_logistica.model.entity.HistorialEstadoRecoleccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HistorialEstadoRecoleccionRepository extends JpaRepository<HistorialEstadoRecoleccion, Long> {

    List<HistorialEstadoRecoleccion> findByRecoleccionIdOrderByTimestampCambioDesc(Long recoleccionId);

    Optional<HistorialEstadoRecoleccion> findByIdAndRecoleccionId( Long recoleccionId, Long estadoId);

}
