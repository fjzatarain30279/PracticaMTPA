/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupo9;

import java.util.ArrayList;
import com.mycompany.grupo9.paquetes.PaqueteUsr;


/**
 * Clase que implementa el decodificador de usuarios
 * @author Javier Zataraín
 * @author Blanca Jorge
 */
public class DecodificadorUsr implements Decodificador {
    /**
     * Decodifica una cadena de texto en un objeto de la clase
     * @param linea Línea de texto que contiene los datos del usuario
     * @return paquete Objeto que contiene los datos decodificados
     */
    @Override
    public PaqueteUsr decodificar(String linea){
        PaqueteUsr paquete = new PaqueteUsr();
        ArrayList<String> lista = new ArrayList<String>();
        String[] parcial = linea.split("-");
        String[] usuarios = parcial[1].split(";");
        paquete.setSeleccionado(usuarios[0]);
        paquete.setSeleccionador(usuarios[1]);
        String[] aux = parcial[0].split(";");
        for(String usr: aux){
            lista.add(usr);
        }
        paquete.setListaUsuarios(lista);
        return paquete;
    }
    
}
