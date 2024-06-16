}/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cliente.vista;

import javax.swing.JFrame;

/**
 * Clase que gestiona la visibilidad del menú de inicio, los usuarios 
 * disponibles y el tablero de la partida
 * @author Javier Zataraín
 * @author Blanca Jorge
 */
public class GestorVistas {
    private JFrame estadoActual;
    
    /**
     * Muestra la vista del menu y cierra las otras
     */
    public void mostrarVistaMenu() {
        if (estadoActual!=null) {
            estadoActual.setVisible(false);
            estadoActual.dispose();
        }
        estadoActual=new VistaMenuInicial();
        estadoActual.setVisible(true);
    }
    /**
     * Muestra la vista de la lista de usuarios y cierra las otras
     */
    public void mostrarVistaUsuarios() {
        if (estadoActual!=null) {
            estadoActual.setVisible(false);
            estadoActual.dispose();
        }
        estadoActual= new VistaUsuarios();
        estadoActual.setVisible(true);
    }
    /**
     * Muestra la vista del tablero de juego y cierra las otras
     */
    public void mostrarVistaTablero() {
        if (estadoActual!=null) {
            estadoActual.setVisible(false);
            estadoActual.dispose();
        }
        estadoActual= new VistaTablero();
        estadoActual.setVisible(true);
    }
}
