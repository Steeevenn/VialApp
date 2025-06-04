package com.co.vialogistic.sistema_gestion_logistica.model.entity;

import com.co.vialogistic.sistema_gestion_logistica.model.enums.EstadoRecoleccion;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "historial_estados_recoleccion")
@Getter
@Setter
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
}


