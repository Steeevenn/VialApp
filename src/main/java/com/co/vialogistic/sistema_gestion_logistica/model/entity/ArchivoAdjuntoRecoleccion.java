package com.co.vialogistic.sistema_gestion_logistica.model.entity;


import com.co.vialogistic.sistema_gestion_logistica.model.enums.TipoArchivo;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "archivos_adjuntos_recoleccion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArchivoAdjuntoRecoleccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recoleccion_id", nullable = false)
    private Recoleccion recoleccion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_archivo", nullable = false, length = 40)
    private TipoArchivo tipoArchivo;

    @Column(name = "url_archivo", nullable = false, length = 512)
    private String urlArchivo;

    @Column(name = "nombre_original_archivo")
    private String nombreOriginalArchivo;

    @Column(name = "content_type", length = 100)
    private String contentType;

    @Column(name = "tamano_bytes")
    private Long tamanoBytes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subido_por_usuario_id")
    private Usuario subidoPorUsuario;

    @CreationTimestamp
    @Column(name = "fecha_subida", nullable = false, updatable = false, columnDefinition = "TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP")
    private OffsetDateTime fechaSubida;


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

    public TipoArchivo getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(TipoArchivo tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public String getUrlArchivo() {
        return urlArchivo;
    }

    public void setUrlArchivo(String urlArchivo) {
        this.urlArchivo = urlArchivo;
    }

    public String getNombreOriginalArchivo() {
        return nombreOriginalArchivo;
    }

    public void setNombreOriginalArchivo(String nombreOriginalArchivo) {
        this.nombreOriginalArchivo = nombreOriginalArchivo;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getTamanoBytes() {
        return tamanoBytes;
    }

    public void setTamanoBytes(Long tamanoBytes) {
        this.tamanoBytes = tamanoBytes;
    }

    public Usuario getSubidoPorUsuario() {
        return subidoPorUsuario;
    }

    public void setSubidoPorUsuario(Usuario subidoPorUsuario) {
        this.subidoPorUsuario = subidoPorUsuario;

    }

    public OffsetDateTime getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(OffsetDateTime fechaSubida) {
        this.fechaSubida = fechaSubida;
    }
}
