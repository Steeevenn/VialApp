package com.co.vialogistic.sistema_gestion_logistica.repository;

import com.co.vialogistic.sistema_gestion_logistica.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {

}
