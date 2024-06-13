package com.mycompany.grupo9;

import java.io.IOException;
import com.mycompany.grupo9.paquetes.PaqueteLogin;
import com.mycompany.grupo9.ManejadorCliente;
import com.mycompany.grupo9.paquetes.PaquetePartida;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ControladorServidor
        implements Runnable {

    private ServerSocket servidor;
    private static HashMap<String, String> listaUsuarios = new HashMap<String, String>();
    private static ArrayList<ManejadorCliente> listaManejadores = new ArrayList<ManejadorCliente>();
    private static ArrayList<PaquetePartida> listaPartidas = new ArrayList<PaquetePartida>();
    private static ArrayList<String> ranking;
    private Thread t;
    private volatile boolean active = true;

    public ControladorServidor() throws Exception {
        servidor = new ServerSocket(2000);
        t = new Thread(this, "ControladorServidor");
        t.start();
    }

    public void run() {
        try {
            startListeningUsers();
        } catch (Exception e) {
        }
    }

    public void startListeningUsers() throws Exception {
        setListaUsuarios(LectorFicheros.lecturaFichero("Usuarios.txt"));
        setRanking(LectorFicheros.lecturaPuntuaciones("Puntuaciones.txt"));
        try {
            while (isActive()) {
                mostrarMenu();
                System.out.println("Esperando clientes....");
                Socket sck = servidor.accept();
                System.out.println("\nUn cliente conectado...");
                ManejadorCliente manejador = new ManejadorCliente(sck);
            }
        } catch (java.net.SocketException se) {
            // Si se lanza una SocketException, significa que el servidor se cerró
            System.out.println("El servidor se ha detenido.");
        }
        System.out.println("server.ControladorServidor.startListeningUsers()");
    }

    public static void difusionMensaje(byte[] mensaje) {
        for (ManejadorCliente unCliente : listaManejadores) {
            try {
                unCliente.sendMessage(mensaje);
            } catch (Exception e) {
                System.out.println("Err. Difusion: " + e.toString());
            }

        }
    }

    public static synchronized void creaUsr(String usr, String pwd) {
        listaUsuarios.put(usr, pwd);
    }

    public static int compruebaCrear(String usr, String pwd) {
        if (listaUsuarios.containsKey(usr) == false) {
            return 1;
        } else if (listaUsuarios.get(usr).equals(pwd) == false) {
            return 2;
        } else {
            return 3;
        }
    }
    public static int compruebaLogin(String usr, String pwd) {
        if (listaUsuarios.containsKey(usr) && listaUsuarios.get(usr).equals(pwd)) {
            return 3;
        }else {
            return 1;
        }
    }

    public void guardaDatos() {
        LectorFicheros.guardaUsr("Usuarios.txt", listaUsuarios);
        LectorFicheros.guardaPuntuaciones("Puntuaciones.txt", ranking);
        LectorFicheros.guardaPartidas("Partidas.txt", listaPartidas);
    }

    public void stopServidor() throws IOException {
        setActive(false);
        servidor.close(); // Close the ServerSocket
        getT().interrupt();
    }

    public HashMap<String, String> getListaUsuarios() {
        return listaUsuarios;
    }

    public String getConectados() {
        ArrayList<String> conectados = new ArrayList<String>();
        for (ManejadorCliente m : listaManejadores) {
            conectados.add(m.getName());
        }
        return conectados.toString();
    }

    public void setListaUsuarios(HashMap<String, String> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public static ArrayList<ManejadorCliente> getListaManejadores() {
        return listaManejadores;
    }

    public static void setListaManejadores(ArrayList<ManejadorCliente> listaManejadores) {
        ControladorServidor.listaManejadores = listaManejadores;
    }

    public static ArrayList<PaquetePartida> getListaPartidas() {
        return listaPartidas;
    }

    public static void setListaPartidas(ArrayList<PaquetePartida> listaPartidas) {
        ControladorServidor.listaPartidas = listaPartidas;
    }

    public static ArrayList<String> getRanking() {
        return ranking;
    }

    public static void setRanking(ArrayList<String> ranking) {
        ControladorServidor.ranking = ranking;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;

    }

    public Thread getT() {
        return t;
    }

    public static void mostrarMenu() {
        System.out.println("=== Menú de Opciones ===");
        System.out.println("1. Obtener listado de usuarios registrados del servidor");
        System.out.println("2. Obtener listado de usuarios en linea");
        System.out.println("3. Obtener listado de las partidas del servidor");
        System.out.println("4. Obtener Informacion de una partida concreta");
        System.out.println("0. Salir");
    }

    public static void addManejador(ManejadorCliente c) {
        listaManejadores.add(c);
    }
    public static ManejadorCliente getManejador(String s){
        ManejadorCliente m = null;
        for(ManejadorCliente c : listaManejadores){
            if(c.getName().equalsIgnoreCase(s)){
                m = c;
            }
        }
        return m;
    }

    public static PaquetePartida creaPartida(String seleccionado, String seleccionador){
        PaquetePartida p = new PaquetePartida(seleccionado, seleccionador);
        ManejadorCliente c1 = getManejador(seleccionado);
        listaPartidas.add(p);
        c1.enviaPartida(p);
        return p;
    }
}
