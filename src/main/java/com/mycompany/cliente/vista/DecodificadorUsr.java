/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cliente.vista;

import java.util.ArrayList;
import com.mycompany.cliente.modelo.PaqueteUsr;


/**
 * Clase que implementa la decodificación de los usuarios de cadena a objeto de 
 * la clase
 * @author Javier Zataraín
 * @author Blanca Jorge
 */
public class DecodificadorUsr implements Decodificador {
    /**
     * Método que decodifica una cadena de carácteres a un objeto de la clase
     * @param linea Contiene la información del usuario separadas por ;
     * @return paquete Objeto con los datos decodificados
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
