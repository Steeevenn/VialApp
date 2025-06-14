package com.co.vialogistic.sistema_gestion_logistica.model.entity;

import com.co.vialogistic.sistema_gestion_logistica.model.enums.TipoVia;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Table(name = "direcciones")
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
    private String complemento; // Ej: "Apartamento 301 a media cuadra de oxxo", "Local 5"

    @Column(name = "barrio", length = 100)
    private String barrio;

    @Column(name = "codigo_postal", length = 10)
    private String codigoPostal;

    @Column(name = "ciudad", nullable = false)
    private String ciudad;

    @Column(name = "referencias_adicionales", columnDefinition = "TEXT")
    private String referenciasAdicionales;

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false, updatable = false, columnDefinition = "TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP")
    private OffsetDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_actualizacion", nullable = false, columnDefinition = "TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP")
    private OffsetDateTime fechaActualizacion;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoVia getTipoVia() {
        return tipoVia;
    }

    public void setTipoVia(TipoVia tipoVia) {
        this.tipoVia = tipoVia;
    }

    public String getNumeroViaPrincipal() {
        return numeroViaPrincipal;
    }

    public void setNumeroViaPrincipal(String numeroViaPrincipal) {
        this.numeroViaPrincipal = numeroViaPrincipal;
    }

    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public void setNumeroPlaca(String numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getReferenciasAdicionales() {
        return referenciasAdicionales;
    }

    public void setReferenciasAdicionales(String referenciasAdicionales) {
        this.referenciasAdicionales = referenciasAdicionales;
    }

    public OffsetDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(OffsetDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public OffsetDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(OffsetDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

}
