package com.co.vialogistic.sistema_gestion_logistica.model.enums;

public enum EstadoRecoleccion {
    PENDIENTE_ASIGNACION,
    ASIGNADA,
    EN_RUTA_RECOLECCION,
    RECOGIDA,
    EN_BODEGA,
    INCIDENCIA_RECOLECCION,
    CANCELADA;


//Metodo para validar relacion validas al momento de cambiar el estado de mi recoleccion

    public boolean puedeCambiarEstadoRecoleccionSi ( EstadoRecoleccion estadoRecoleccion ){

        if(estadoRecoleccion == null) return false;
        // evitamos cambios iguales en el historial
        if(this == estadoRecoleccion) return false;

        return switch(this){
            case PENDIENTE_ASIGNACION -> estadoRecoleccion == ASIGNADA || estadoRecoleccion == CANCELADA ;
            case ASIGNADA -> estadoRecoleccion == EN_RUTA_RECOLECCION || estadoRecoleccion == INCIDENCIA_RECOLECCION || estadoRecoleccion == CANCELADA || estadoRecoleccion == RECOGIDA;
            case EN_RUTA_RECOLECCION -> estadoRecoleccion == RECOGIDA || estadoRecoleccion == INCIDENCIA_RECOLECCION || estadoRecoleccion == CANCELADA ;
            case RECOGIDA -> estadoRecoleccion == EN_BODEGA || estadoRecoleccion == INCIDENCIA_RECOLECCION;
            case INCIDENCIA_RECOLECCION -> estadoRecoleccion == ASIGNADA || estadoRecoleccion == CANCELADA ;
            case EN_BODEGA, CANCELADA -> false;
        };
    }

}




