package com.co.vialogistic.sistema_gestion_logistica.model.entity;

import com.co.vialogistic.sistema_gestion_logistica.model.enums.EstadoRecoleccion;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "historial_estados_recoleccion")
@NoArgsConstructor
@AllArgsConstructor
public class HistorialEstadoRecoleccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recoleccion_id", nullable = false)
    private Recoleccion recoleccion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_anterior", length = 30)
    private EstadoRecoleccion estadoAnterior;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_nuevo", nullable = false, length = 30)
    private EstadoRecoleccion estadoNuevo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cambiado_por_usuario_id")
    private Usuario cambiadoPorUsuario;    // Si usuario hace cambios en la recoleccion, se registra el usuario que hizo el cambio con fecha y nuevo estado

    @Column(name = "motivo_cambio", columnDefinition = "TEXT")
    private String motivoCambio;

    @CreationTimestamp
    @Column(name = "timestamp_cambio", nullable = false, updatable = false, columnDefinition = "TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP")
    private OffsetDateTime timestampCambio;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Recoleccion getRecoleccion() {
        return recoleccion;
    }

    public void setRecoleccion(Recoleccion recoleccion) {
        this.recoleccion = recoleccion;
    }

    public EstadoRecoleccion getEstadoAnterior() {
        return estadoAnterior;
    }

    public void setEstadoAnterior(EstadoRecoleccion estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    public EstadoRecoleccion getEstadoNuevo() {
        return estadoNuevo;
    }

    public void setEstadoNuevo(EstadoRecoleccion estadoNuevo) {
        this.estadoNuevo = estadoNuevo;
    }

    public Usuario getCambiadoPorUsuario() {
        return cambiadoPorUsuario;
    }

    public void setCambiadoPorUsuario(Usuario cambiadoPorUsuario) {
        this.cambiadoPorUsuario = cambiadoPorUsuario;
    }

    public String getMotivoCambio() {
        return motivoCambio;
    }

    public void setMotivoCambio(String motivoCambio) {
        this.motivoCambio = motivoCambio;
    }

    public OffsetDateTime getTimestampCambio() {
        return timestampCambio;
    }

    public void setTimestampCambio(OffsetDateTime timestampCambio) {
        this.timestampCambio = timestampCambio;
    }
}


