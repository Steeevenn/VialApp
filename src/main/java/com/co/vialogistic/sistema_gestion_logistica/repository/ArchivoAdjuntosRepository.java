package com.co.vialogistic.sistema_gestion_logistica.repository;

import com.co.vialogistic.sistema_gestion_logistica.model.entity.ArchivoAdjuntoRecoleccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArchivoAdjuntosRepository extends JpaRepository<ArchivoAdjuntoRecoleccion, Long> {


    List<ArchivoAdjuntoRecoleccion> findByRecoleccionId(Long recoleccionId);

}
