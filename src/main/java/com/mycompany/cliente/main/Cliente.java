/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.cliente.main;
import com.mycompany.cliente.modelo.PaqueteLogin;
import com.mycompany.cliente.modelo.PaquetePartida;
import com.mycompany.cliente.modelo.PaqueteUsr;
import com.mycompany.cliente.vista.GestorVistas;
import java.net.Socket;

/**
 * La clase Cliente se encarga de inicializar las capas del modelo MVC (las capas modelo, vista y controlador),
 * que son necesarios para ejecutar la aplicación cliente.
 * @author Javier Zatarain
 * @author Blanca Jorge
 */
public class Cliente {
    
    private static GestorVistas gestor;
    private static PaquetePartida modeloPartida;
    private static PaqueteLogin modeloLogin;
    private static PaqueteUsr modeloUsr;
    private static Socket socketCliente;
  

    /**
    * Es el método principal de la clase Cliente. Este inicializa las capas:
    *      Inicializa el gestor de vistas creando una instancia de {@link GestorVistas}.
    *      Inicializa el modelo de partida creando una instancia de {@link PaquetePartida}.
    *      Inicializa el modelo de login creando una instancia de {@link PaqueteLogin}.
    *      Inicializa el modelo de usuario creando una instancia de {@link PaqueteUsr}.
    * Y muestra la vista del menú principal utilizando el método {@link GestorVistas#mostrarVistaMenu()}.
    *@param args vector que contiene todos los argumentos que se pasan en el código.
    */
    public static void main(String[] args) {
              
        gestor = new GestorVistas();
        modeloPartida = new PaquetePartida();
        modeloLogin = new PaqueteLogin();
        modeloUsr = new PaqueteUsr();
        gestor.mostrarVistaMenu();
    } 
    /**
     * Metodo encargado de devolver el gestor de Vistas
     * @return gestor
     */
    public static GestorVistas getGestorVistas() {
        return gestor;
    }  
    /**
     * Metodo encargado de devolver el Modelo de la partida
     * @return modelo de partida
     */
    public static PaquetePartida getModeloPartida() {
        return modeloPartida;
    }
    /**
     * Metodo encargado de devolver el Modelo del usuario
     * @return modelo de usuario
     */
    public static PaqueteUsr getModeloUsr() {
        return modeloUsr;
    }
    /**
     * Metodo encargado de devolver el Modelo del inicio de sesión
     * @return modelo de inicio
     */
    public static PaqueteLogin getModeloLogin() {
        return modeloLogin;
    }
    /**
     * Metodo encargado de establecer el socket del cliente
     * @param s se refiere al socket del cliente
     */
    public static void setSocket(Socket s){
        socketCliente = s;
    }
    /**
     * Metodo encargado de devolver el socket del cliente
     * @return socket del cliente
     */
    public static Socket getSocket(){
        return socketCliente;
    }
    

}
