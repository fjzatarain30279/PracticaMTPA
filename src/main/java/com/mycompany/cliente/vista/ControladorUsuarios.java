/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cliente.vista;

import com.mycompany.grupo9.DecodificadorUsr;
import com.mycompany.cliente.main.Cliente;
import com.mycompany.cliente.modelo.PaqueteUsr;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.ListModel;

/**
 *
 * @author javier
 */
public class ControladorUsuarios {
    private VistaUsuarios vista;
    private PaqueteUsr modelo;
    public static final int PUERTO = 2000;

    public ControladorUsuarios(VistaUsuarios vista) {
        this.vista = vista;
        modelo = Cliente.getModeloUsr();
        

    }
    
    public void procesaEventoSeleccion(int indice){
        String usr = modelo.getElementAt(indice);
        try{
            Socket miSocket = Cliente.getSocket();
            PrintStream o = new PrintStream(miSocket.getOutputStream());
            o.println(usr);
            o.flush();
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void procesaEventoActualizar(){
        java.io.PrintStream o = null;
        
        DecodificadorUsr dec = new DecodificadorUsr();
        try {
            Socket miSocket = Cliente.getSocket();
            o = new java.io.PrintStream(miSocket.getOutputStream());
            o.println("S");
            System.out.println("Controlador Usuarios " + miSocket.toString());    
            BufferedReader inred = new java.io.BufferedReader(new java.io.InputStreamReader(miSocket.getInputStream()));
            modelo.setListaUsuarios(dec.decodificar(inred.readLine()).getListaUsuarios());
            vista.actualizar(modelo.getListaUsuarios());
        } catch (IOException ex) {
            
        }
        
    }
    /*
    public void cerrarConexion(){
        try {
            if (inred != null) {
                inred.close();
            }
            if (outred != null) {
                outred.close();
            }
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            System.out.println("Conexión cerrada.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
}
