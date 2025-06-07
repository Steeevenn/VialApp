package com.co.vialogistic.sistema_gestion_logistica.util;

import com.co.vialogistic.sistema_gestion_logistica.model.entity.Rol;
import com.co.vialogistic.sistema_gestion_logistica.model.enums.RolNombre;
import com.co.vialogistic.sistema_gestion_logistica.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class InserccionBdRoles  {

    private final RoleRepository roleRepository;

    public InserccionBdRoles(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Llamda del metodo al iniciar la aplicacion con anotacion PostConstruct
    @PostConstruct
    public void cargarRoles() {

        List<RolNombre> roles = Arrays.asList(
                RolNombre.ADMINISTRADOR,
                RolNombre.DOMICILIARIO
        );

        for(RolNombre rolNombre : roles){
            Rol rol = new Rol();
            if (!roleRepository.existsByNombre(rolNombre)){
                rol.setNombre(rolNombre);
                roleRepository.save(rol);
            }else{
                System.out.println("Usuario ya existe en el sistema" );
            }
        }


    }

}

