package com.co.vialogistic.sistema_gestion_logistica.model.entity;


import com.co.vialogistic.sistema_gestion_logistica.model.enums.EstadoRecoleccion;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;


@Entity
@Table(name = "recolecciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recoleccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "administrador_agendo_id", nullable = false)
    private Usuario administradorAgendo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "domiciliario_asignado_id")
    private Usuario domiciliarioAsginado;

    @Column(name = "nombre_remitente", nullable = false, length = 100)
    private String nombreRemitente;

    @Column(name = "telefono_remitente", length = 20)
    private String telefonoRemitente;

    @Column(name = "email_remitente", length = 100, nullable = false)
    private String emailRemitente;

    @Column(name = "nombre_destinatario", nullable = false, length = 100)
    private String nombreDestinatario;

    @Column(name = "telefono_destinatario", length = 20)
    private String telefonoDestinatario;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "direccion_recoleccion_id", nullable = false)
    private Direccion direccionRecoleccion;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "direccion_entrega_id")
    private Direccion direccionEntrega;

    // Detalles del Paquete
    @Column(name = "descripcion_paquete", columnDefinition = "TEXT")
    private String descripcionPaquete;

    @Column(name = "peso_kg", precision = 10, scale = 2)
    private BigDecimal pesoKg;

    @Column(name = "alto_cm", precision = 7, scale = 2)
    private BigDecimal altoCm;

    @Column(name = "ancho_cm", precision = 7, scale = 2)
    private BigDecimal anchoCm;

    @Column(name = "largo_cm", precision = 7, scale = 2)
    private BigDecimal largoCm;

    //Programacion y Estado
    @Column(name = "fecha_hora_programada_recoleccion", nullable = false, columnDefinition = "TIMESTAMPTZ")
    private OffsetDateTime fechaHoraProgramadaRecoleccion;

    @Column(name = "fecha_hora_recoleccion_real", columnDefinition = "TIMESTAMPTZ")
    private OffsetDateTime fechaHoraRecoleccionReal;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_recoleccion", nullable = false, length = 30)
    private EstadoRecoleccion estadoRecoleccion = EstadoRecoleccion.PENDIENTE_ASIGNACION;

    @Column(name = "notas_domiciliario", columnDefinition = "TEXT")
    private String notasDomiciliario;

    @Column(name = "notas_administrador", columnDefinition = "TEXT")
    private String notasAdministrador;

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false, updatable = false, columnDefinition = "TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP")
    private OffsetDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_actualizacion", nullable = false, columnDefinition = "TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP")
    private OffsetDateTime fechaActualizacion;

    @OneToMany(mappedBy = "recoleccion", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ArchivoAdjuntoRecoleccion> archivosAdjuntos = new ArrayList<>();

    @OneToMany(mappedBy = "recoleccion", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<HistorialEstadoRecoleccion> historialEstados = new ArrayList<>();
















}
