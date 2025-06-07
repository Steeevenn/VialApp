package com.co.vialogistic.sistema_gestion_logistica.model.entity;


import com.co.vialogistic.sistema_gestion_logistica.model.enums.RolNombre;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Data
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true, length = 15)
    private RolNombre nombre;

    public RolNombre getNombre() {
        return nombre;
    }

    public void setNombre(RolNombre nombre) {
        this.nombre = nombre;
    }
}
