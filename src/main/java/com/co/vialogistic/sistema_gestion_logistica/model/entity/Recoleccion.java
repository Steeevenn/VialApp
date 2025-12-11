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
    private Usuario usuarioAgendoId;

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

    @ManyToOne(fetch = FetchType.LAZY)
    //cambiar a false es solo para pruebas
    @JoinColumn(name = "direccion_destinatario_id", nullable = false )
    private Direccion direccionDestinatario;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "direccion_remitente_id")
    private Direccion direccionRemitente;

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


    @Column(name = "notas_domiciliario", columnDefinition = "TEXT")
    private String notasDomiciliario;

    @Column(name = "notas_administrador", columnDefinition = "TEXT")
    private String notasAdministrador;

    //Programacion y Estado

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_recoleccion", nullable = false, length = 30)
    private EstadoRecoleccion estadoRecoleccion = EstadoRecoleccion.PENDIENTE_ASIGNACION;

    @Column(name = "fecha_hora_programada_recoleccion", nullable = false, columnDefinition = "TIMESTAMPTZ")
    private OffsetDateTime fechaHoraProgramadaRecoleccion;

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Usuario getUsuarioAgendoId() {
        return usuarioAgendoId;
    }

    public void setUsuarioAgendoId(Usuario usuarioAgendoId) {
        this.usuarioAgendoId = usuarioAgendoId;
    }

    public Usuario getDomiciliarioAsginado() {
        return domiciliarioAsginado;
    }

    public void setDomiciliarioAsginado(Usuario domiciliarioAsginado) {
        this.domiciliarioAsginado = domiciliarioAsginado;
    }

    public String getNombreRemitente() {
        return nombreRemitente;
    }

    public void setNombreRemitente(String nombreRemitente) {
        this.nombreRemitente = nombreRemitente;
    }

    public String getTelefonoRemitente() {
        return telefonoRemitente;
    }

    public void setTelefonoRemitente(String telefonoRemitente) {
        this.telefonoRemitente = telefonoRemitente;
    }

    public String getEmailRemitente() {
        return emailRemitente;
    }

    public void setEmailRemitente(String emailRemitente) {
        this.emailRemitente = emailRemitente;
    }

    public String getNombreDestinatario() {
        return nombreDestinatario;
    }

    public void setNombreDestinatario(String nombreDestinatario) {
        this.nombreDestinatario = nombreDestinatario;
    }

    public String getTelefonoDestinatario() {
        return telefonoDestinatario;
    }

    public void setTelefonoDestinatario(String telefonoDestinatario) {
        this.telefonoDestinatario = telefonoDestinatario;
    }

    public Direccion getDireccionDestinatario() {
        return direccionDestinatario;
    }

    public void setDireccionDestinatario(Direccion direccionDestinatario) {
        this.direccionDestinatario = direccionDestinatario;
    }

    public Direccion getDireccionRemitente() {
        return direccionRemitente;
    }

    public Direccion getDireccionEntrega() {
        return direccionRemitente;
    }

    public void setDireccionRemitente(Direccion direccionEntrega) {
        this.direccionRemitente = direccionEntrega;
    }

    public String getDescripcionPaquete() {
        return descripcionPaquete;
    }

    public void setDescripcionPaquete(String descripcionPaquete) {
        this.descripcionPaquete = descripcionPaquete;
    }

    public BigDecimal getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(BigDecimal pesoKg) {
        this.pesoKg = pesoKg;
    }

    public BigDecimal getAltoCm() {
        return altoCm;
    }

    public void setAltoCm(BigDecimal altoCm) {
        this.altoCm = altoCm;
    }

    public BigDecimal getAnchoCm() {
        return anchoCm;
    }

    public void setAnchoCm(BigDecimal anchoCm) {
        this.anchoCm = anchoCm;
    }

    public BigDecimal getLargoCm() {
        return largoCm;
    }

    public void setLargoCm(BigDecimal largoCm) {
        this.largoCm = largoCm;
    }

    public String getNotasDomiciliario() {
        return notasDomiciliario;
    }

    public void setNotasDomiciliario(String notasDomiciliario) {
        this.notasDomiciliario = notasDomiciliario;
    }

    public String getNotasAdministrador() {
        return notasAdministrador;
    }

    public void setNotasAdministrador(String notasAdministrador) {
        this.notasAdministrador = notasAdministrador;
    }

    public EstadoRecoleccion getEstadoRecoleccion() {
        return estadoRecoleccion;
    }

    public void setEstadoRecoleccion(EstadoRecoleccion estadoRecoleccion) {
        this.estadoRecoleccion = estadoRecoleccion;
    }

    public OffsetDateTime getFechaHoraProgramadaRecoleccion() {
        return fechaHoraProgramadaRecoleccion;
    }

    public void setFechaHoraProgramadaRecoleccion(OffsetDateTime fechaHoraProgramadaRecoleccion) {
        this.fechaHoraProgramadaRecoleccion = fechaHoraProgramadaRecoleccion;
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

    public List<ArchivoAdjuntoRecoleccion> getArchivosAdjuntos() {
        return archivosAdjuntos;
    }

    public void setArchivosAdjuntos(List<ArchivoAdjuntoRecoleccion> archivosAdjuntos) {
        this.archivosAdjuntos = archivosAdjuntos;
    }

    public List<HistorialEstadoRecoleccion> getHistorialEstados() {
        return historialEstados;
    }

    public void setHistorialEstados(List<HistorialEstadoRecoleccion> historialEstados) {
        this.historialEstados = historialEstados;
    }

    @Override
    public String toString() {
        return "Recoleccion{" +
                "id=" + id +
                ", usuarioAgendoId=" + usuarioAgendoId +
                ", domiciliarioAsignado=" + domiciliarioAsginado +
                ", nombreRemitente='" + nombreRemitente + '\'' +
                ", telefonoRemitente='" + telefonoRemitente + '\'' +
                ", emailRemitente='" + emailRemitente + '\'' +
                ", nombreDestinatario='" + nombreDestinatario + '\'' +
                ", telefonoDestinatario='" + telefonoDestinatario + '\'' +
                ", direccionDestinatario=" + direccionDestinatario +
                ", direccionRemitente=" + direccionRemitente +
                ", descripcionPaquete='" + descripcionPaquete + '\'' +
                ", pesoKg=" + pesoKg +
                ", altoCm=" + altoCm +
                ", anchoCm=" + anchoCm +
                ", largoCm=" + largoCm +
                ", notasDomiciliario='" + notasDomiciliario + '\'' +
                ", notasAdministrador='" + notasAdministrador + '\'' +
                ", estadoRecoleccion=" + estadoRecoleccion +
                ", fechaHoraProgramadaRecoleccion=" + fechaHoraProgramadaRecoleccion +
                ", fechaCreacion=" + fechaCreacion +
                ", fechaActualizacion=" + fechaActualizacion +
                ", archivosAdjuntos=" + archivosAdjuntos +
                ", historialEstados=" + historialEstados +
                '}';
    }
}
