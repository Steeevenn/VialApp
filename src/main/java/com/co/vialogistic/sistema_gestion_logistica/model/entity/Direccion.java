package com.co.vialogistic.sistema_gestion_logistica.model.entity;

import com.co.vialogistic.sistema_gestion_logistica.model.enums.TipoVia;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "direcciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_via", length = 50, nullable = false)
    private TipoVia tipoVia;

    @Column(name = "numero_via_principal", length = 50)
    private String numeroViaPrincipal; // Ej: 45, 7B, 100

    @Column(name = "numero_placa", length = 50)
    private String numeroPlaca; // Ej: 12-34, 56A

    @Column(name = "complemento", length = 255)
    private String complemento; // Ej: "Edificio Torre Central", "Local 5"

    @Column(name = "barrio", length = 100)
    private String Barrio;

    @Column(name = "codigo_postal", length = 10)
    private String codigoPostal;

    @Column(nullable = false, length = 50)
    private String ciudad;

    @Column(name = "referencias_adicionales", columnDefinition = "TEXT")
    private String referenciasAdicionales;

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false, updatable = false, columnDefinition = "TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP")
    private OffsetDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_actualizacion", nullable = false, columnDefinition = "TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP")
    private OffsetDateTime fechaActualizacion;


}
