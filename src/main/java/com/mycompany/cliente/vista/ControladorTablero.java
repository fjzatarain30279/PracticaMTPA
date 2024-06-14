/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cliente.vista;

import com.mycompany.cliente.main.Cliente;
import com.mycompany.cliente.modelo.PaquetePartida;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author javier
 */
public class ControladorTablero {
    private VistaTablero vista;
    private PaquetePartida modelo;
    public static final int PUERTO = 2000;

    public ControladorTablero(VistaTablero vista) {
        this.vista = vista;
        modelo = Cliente.getModeloPartida();

    }
    
    public void procesaEventoActualizar(){
        java.io.PrintStream o = null;

        DecodificadorPartida dec = new DecodificadorPartida();
        try {
            Socket miSocket = Cliente.getSocket();
            o = new java.io.PrintStream(miSocket.getOutputStream());
            o.println("T;"+Cliente.getModeloLogin().getUsuario());
            BufferedReader inred = new java.io.BufferedReader(new java.io.InputStreamReader(miSocket.getInputStream()));
            String linea = inred.readLine();
            System.out.println(linea);
            modelo = dec.decodificar(linea);
            vista.actualizar(modelo);
        } catch (IOException ex) {

        }

        
    }
    
    public void procesaEventoFinalizar(){
        
    }
    
    public void procesaEventoJugada(int[] j){
        
    }
}
