/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupo9;

import java.util.ArrayList;
import com.mycompany.grupo9.paquetes.PaqueteUsr;


/**
 *
 * @author javier
 */
public class DecodificadorUsr implements Decodificador {
    
    @Override
    public PaqueteUsr decodificar(String linea){
        PaqueteUsr paquete = new PaqueteUsr();
        ArrayList<String> lista = new ArrayList<String>();
        String[] aux = linea.split(";");
        for(String usr: aux){
            lista.add(usr);
        }
        paquete.setListaUsuarios(lista);
        return paquete;
    }
    
}
