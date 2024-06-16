/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupo9;

import com.mycompany.grupo9.paquetes.PaqueteLogin;

/**
 * Clase que implementa el decodificador del login
 * @author Javier Zataraín
 * @author Blanca Jorge
 */
public class DecodificadorLogin implements Decodificador{
    /**
     * Decodifica una cadena de texto en un objeto de la clase
     * @param linea Línea de texto que contiene los datos del usuario
     * @return paquete Objeto que contiene los datos decodificados
     */
    @Override
    public PaqueteLogin decodificar(String linea){
        PaqueteLogin p = new PaqueteLogin();
        String[] aux = linea.split(";");
        p.setUsuario(aux[0]);
        p.setPassword(aux[1]);
        p.setComprobado(Integer.parseInt(aux[2]));
        return p;
    }
}
