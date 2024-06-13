/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cliente.vista;

import com.mycompany.grupo9.*;
import java.util.ArrayList;
import com.mycompany.cliente.modelo.PaqueteUsr;


/**
 *
 * @author javier
 */
public class DecodificadorUsr implements Decodificador {
    
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
