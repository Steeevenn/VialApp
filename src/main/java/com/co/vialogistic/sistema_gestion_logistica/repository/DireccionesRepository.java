package com.co.vialogistic.sistema_gestion_logistica.repository;

import com.co.vialogistic.sistema_gestion_logistica.model.entity.Direccion;
import com.co.vialogistic.sistema_gestion_logistica.model.enums.TipoVia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DireccionesRepository extends JpaRepository<Direccion, Long> {

    @Query("SELECT d FROM Direccion d WHERE " +
            "d.tipoVia = :tipoVia AND " +
            "TRIM(LOWER(d.numeroViaPrincipal)) = TRIM(LOWER(:numeroViaPrincipal)) AND " +
            "TRIM(LOWER(d.numeroPlaca)) = TRIM(LOWER(:numeroPlaca)) AND " +
            "TRIM(LOWER(COALESCE(d.complemento, ''))) = TRIM(LOWER(COALESCE(:complemento, ''))) AND " +
            "TRIM(LOWER(d.barrio)) = TRIM(LOWER(:barrio)) AND " +
            "TRIM(LOWER(d.ciudad)) = TRIM(LOWER(:ciudad))"
    )
    Optional<Direccion> findByCamposUnicos(
            @Param("tipoVia") TipoVia tipoVia,
            @Param("numeroViaPrincipal") String numeroViaPrincipal,
            @Param("numeroPlaca") String numeroPlaca,
            @Param("complemento") String complemento,
            @Param("barrio") String barrio,
            @Param("ciudad") String ciudad
    );


}


