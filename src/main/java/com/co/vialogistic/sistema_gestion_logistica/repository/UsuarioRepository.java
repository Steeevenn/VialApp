package com.co.vialogistic.sistema_gestion_logistica.repository;

import com.co.vialogistic.sistema_gestion_logistica.model.entity.Usuario;
import com.co.vialogistic.sistema_gestion_logistica.model.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);


    // Metodo para buscar los usuarios con el rol de domiciliario
    @Query("SELECT u FROM Usuario u JOIN u.roles r WHERE r.nombre = 'DOMICILIARIO'")
    List<Usuario> findAllDomiciliarios();


}
