/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupo9;

import com.mycompany.grupo9.paquetes.PaqueteLogin;

/**
 *
 * @author javier
 */
public class DecodificadorLogin implements Decodificador{
    
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
