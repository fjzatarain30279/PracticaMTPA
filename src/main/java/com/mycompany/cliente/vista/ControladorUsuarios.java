/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cliente.vista;

import com.mycompany.cliente.main.Cliente;
import com.mycompany.cliente.modelo.PaqueteUsr;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Clase que controla el menú y se encarga de procesar el inicio de sesión y la
 * crecaión de usuarios
 * @author Javier Zataraín
 * @author Blanca Jorge
 */
public class ControladorUsuarios {

    private VistaUsuarios vista;
    private PaqueteUsr modelo;
    public static final int PUERTO = 2000;

    public ControladorUsuarios(VistaUsuarios vista) {
        this.vista = vista;
        modelo = Cliente.getModeloUsr();

    }
    /**
     * Método encargado de procesar la espera antes de iniciar una partida
     */
    public void procesaEventoEsperar() {
        BufferedReader inred = null;
        try {
            String linea;
            Socket miSocket = Cliente.getSocket();
            inred = new java.io.BufferedReader(new java.io.InputStreamReader(miSocket.getInputStream()));
            linea = inred.readLine();
            if (linea.equalsIgnoreCase("P")) {
                Cliente.getGestorVistas().mostrarVistaTablero();
            }
        } catch (IOException ex) {
        }

    }
    /**
     * Método que procesa la selección de un usuario por parte de otro
     * @param indice posición del usuario en la lista
     */
    public void procesaEventoSeleccion(int indice) {
        String usr = modelo.getElementAt(indice);
        if (!usr.equalsIgnoreCase(Cliente.getModeloLogin().getUsuario())) {
            try {
                Socket miSocket = Cliente.getSocket();
                PrintStream o = new PrintStream(miSocket.getOutputStream());
                modelo.setSeleccionado(usr);
                modelo.setSeleccionador(Cliente.getModeloLogin().getUsuario());
                o.println("U" + modelo.toString());
                o.flush();
                String linea;
                BufferedReader inred = new java.io.BufferedReader(new java.io.InputStreamReader(miSocket.getInputStream()));
                linea = inred.readLine();
                if (linea.equalsIgnoreCase("P")) {
                    Cliente.getGestorVistas().mostrarVistaTablero();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            vista.setErrMessage("Es su propio usuario");
        }
    }
    /**
     * Método que procesa las actualizaciones de los usuarios
     */
    public void procesaEventoActualizar() {
        java.io.PrintStream o = null;

        DecodificadorUsr dec = new DecodificadorUsr();
        try {
            Socket miSocket = Cliente.getSocket();
            o = new java.io.PrintStream(miSocket.getOutputStream());
            o.println("A");
            System.out.println("Controlador Usuarios buscando actualizar...");
            BufferedReader inred = new java.io.BufferedReader(new java.io.InputStreamReader(miSocket.getInputStream()));
            modelo.setListaUsuarios(separar(inred.readLine()));
            vista.actualizar(modelo.getListaUsuarios());
        } catch (IOException ex) {

        }

    }
    /**
     * Método que separa el usuario localizado en las listas
     * @param linea Cadena donde separado por ; encontramos al usuario
     * @return usr El usuario 
     */
    public ArrayList<String> separar(String linea) {
        ArrayList<String> usr = new ArrayList<String>();
        for (String s : linea.split(";")) {
            usr.add(s);
        }
        return usr;
    }
    public void procesaEventoCerrar() {
        java.io.PrintStream o = null;
        try {
            Socket miSocket = Cliente.getSocket();
            o = new java.io.PrintStream(miSocket.getOutputStream());
            o.println("X");
        } catch (IOException e) {

        }
    }
}
