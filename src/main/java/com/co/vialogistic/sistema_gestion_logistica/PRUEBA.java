package com.co.vialogistic.sistema_gestion_logistica;

public class PRUEBA {

    public static void main(String[] args) {

        int i;
        boolean b;
        String myString = "Hola Mundo y Hola Mundo";


        // Metodo para ignorar si es mayuscula o minuscula la comparacion
        b = myString.equalsIgnoreCase("HOLAMUNDO");

        //   System.out.println(b);

        i = myString.lastIndexOf("Hola");
        System.out.println(i);
    }
}
