package com.co.vialogistic.sistema_gestion_logistica.model.entity;

import com.co.vialogistic.sistema_gestion_logistica.model.enums.TipoNotificacion;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "notificaciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recoleccion_id") // Puede ser nulo si la notificación no está ligada a una recolección específica
    private Recoleccion recoleccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enviada_por_usuario_id", nullable = false)
    private Usuario enviadaPorUsuario; // Usuario que envía la notificación

    @Column(name = "contacto_externo")
    private String contactoExterno;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_notificacion", nullable = false, length = 20) // Ajusta length
    private TipoNotificacion tipoNotificacion;

    @Column(name = "mensaje_contenido", columnDefinition = "TEXT")
    private String mensajeContenido;

    @CreationTimestamp
    @Column(name = "fecha_envio", nullable = false, updatable = false, columnDefinition = "TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP")
    private OffsetDateTime fechaEnvio;

    @Column(columnDefinition = "BOOLEAN")
    private Boolean exitoso;

    @Column(name = "respuesta_proveedor", columnDefinition = "TEXT")
    private String respuestaProveedor;
}
