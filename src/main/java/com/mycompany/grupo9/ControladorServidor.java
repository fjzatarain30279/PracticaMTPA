package com.mycompany.grupo9;

import java.io.IOException;
import com.mycompany.grupo9.paquetes.PaqueteLogin;
import com.mycompany.grupo9.ManejadorCliente;
import com.mycompany.grupo9.paquetes.PaquetePartida;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Clase que controla el servidor, gestiona la conexión de clientes,
 * la difusión de mensajes y la administración de usuarios y partidas
 * @author Javier Zataraín
 * @author Blanca Jorge
 */
public class ControladorServidor
        implements Runnable {

    private ServerSocket servidor;
    private static HashMap<String, String> listaUsuarios = new HashMap<String, String>();
    private static ArrayList<ManejadorCliente> listaManejadores = new ArrayList<ManejadorCliente>();
    private static ArrayList<PaquetePartida> listaPartidas = new ArrayList<PaquetePartida>();
    private static ArrayList<String> ranking;
    private Thread t;
    private volatile boolean active = true;
    /**
     * Método que crea el canal de comunicación del servidor en el puerto 2000 y
     * comienza el hilo.
     * @throws Exception Si se da un error al crear ServerSocket. 
     */
    public ControladorServidor() throws Exception {
        servidor = new ServerSocket(2000);
        t = new Thread(this, "ControladorServidor");
        t.start();
    }
    /** 
     * Método que inicia la escucha de usuarios
     */
    public void run() {
        try {
            startListeningUsers();
        } catch (Exception e) {
        }
    }
    /**
     * Método que acepta nuevas conexiones de usuarios
     * @throws Exception Si ocurre un error al intentar leer el archivo de 
     * usuarios o puntuaciones
     */
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
    /**
     * Método encargardo de dinfundir un mensaje a todos los usuarios conectados
     * @param mensaje Lo que se quiera compartir
     */
    public static void difusionMensaje(byte[] mensaje) {
        for (ManejadorCliente unCliente : listaManejadores) {
            try {
                unCliente.sendMessage(mensaje);
            } catch (Exception e) {
                System.out.println("Err. Difusion: " + e.toString());
            }

        }
    }
    /**
     * Busca una partida iniciada del usuario.
     * @param usr El nombre del usuario.
     * @return p si la partida existe, null si la partida no existe.
     */
    public static synchronized PaquetePartida buscaTablero(String usr){
        PaquetePartida partida = new PaquetePartida();
        for(PaquetePartida p : listaPartidas){
            if(p.getJug1().equalsIgnoreCase(usr)||p.getJug2().equalsIgnoreCase(usr) && !p.isFinalizada()){
                partida = p;
                break;
            }
            else{
                partida = null;
            }
        }
        return partida;
    }
    /**
     * Crea un nuevo usuario
     * @param usr Nombre de usuario
     * @param pwd Contraseña 
     */
    public static synchronized void creaUsr(String usr, String pwd) {
        listaUsuarios.put(usr, pwd);
    }
    /**
     * Verifica si existe o no el usuario creado.
     * @param usr Nombre usuario
     * @param pwd Contraseña
     * @return 1 si el usuario no es correcto, 2 si la contraseña no coincide,
     * 3 si es correcto.
     */
    public static int compruebaCrear(String usr, String pwd) {
        if (listaUsuarios.containsKey(usr) == false) {
            return 1;
        } else if (listaUsuarios.get(usr).equals(pwd) == false) {
            return 2;
        } else {
            return 3;
        }
    }
    /**
     * Comprueba que las credenciales introducidas corresponden a un usuario 
     * registrado
     * @param usr Nombre usuario
     * @param pwd Contraseña
     * @return 3 si las credenciales son correctas, 1 si el usuario no existe o
     * la contraseña no coincide con la del usuario
     */
    public static int compruebaLogin(String usr, String pwd) {
        if (listaUsuarios.containsKey(usr) && listaUsuarios.get(usr).equals(pwd)) {
            return 3;
        }else {
            return 1;
        }
    }
    /**
     * Encargado de guardar los datos referentes a usuarios, puntuaciones y partidas
     * en sus ficheros de persistencia correspondientes.
     */
    public void guardaDatos() {
        LectorFicheros.guardaUsr("Usuarios.txt", listaUsuarios);
        LectorFicheros.guardaPuntuaciones("Puntuaciones.txt", ranking);
        LectorFicheros.guardaPartidas("Partidas.txt", listaPartidas);
    }
    /**
     * Finaliza el servidor y cierra el ServerSocket
     * @throws IOException Si ocurre un error al intentar cerrarlo
     */
    public void stopServidor() throws IOException {
        setActive(false);
        servidor.close(); // Close the ServerSocket
        getT().interrupt();
    }
    /**
     * Muestra una lista de los usuarios
     * @return listaUsuarios hasmap de los usuarios registrados
     */
    public HashMap<String, String> getListaUsuarios() {
        return listaUsuarios;
    }
    /**
     * Muestra una lista con los usuarios conectados en ese instante
     * @return conectados.toString cadena con los usuarios conectados
     */
    public String getConectados() {
        ArrayList<String> conectados = new ArrayList<String>();
        for (ManejadorCliente m : listaManejadores) {
            conectados.add(m.getName());
        }
        return conectados.toString();
    }
    /**
     * Establece una lista con los usuarios registrados
     * @param listaUsuarios HashMap de los usuarios disponibles
     */
    public void setListaUsuarios(HashMap<String, String> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    /**
     * Obtiene una lista con los manejadores de clientes
     * @return listaManejadores Listado de manejadores de clientes
     */
    public static ArrayList<ManejadorCliente> getListaManejadores() {
        return listaManejadores;
    }
    /**
     * Establece la lista de manejadores de clientes
     * @param listaManejadores Lista de los manejadores
     */
    public static void setListaManejadores(ArrayList<ManejadorCliente> listaManejadores) {
        ControladorServidor.listaManejadores = listaManejadores;
    }
    /**
     * Obtiene una lista con todas las partidas disponibles
     * @return listaParcidas Listado de partidas
     */
    public static ArrayList<PaquetePartida> getListaPartidas() {
        return listaPartidas;
    }
    /**
     * Establece la lista de partidas disponbles
     * @param listaPartidas Listado de partidas disponibles
     */
    public static void setListaPartidas(ArrayList<PaquetePartida> listaPartidas) {
        ControladorServidor.listaPartidas = listaPartidas;
    }
    /**
     * Obtiene el ranking de los usuarios
     * @return ranking Puntuaciones de los usuarios
     */
    public static ArrayList<String> getRanking() {
        return ranking;
    }
    /**
     * Esyablece un ranking de los usuarios según sus puntuaciones
     * @param ranking Ordenación de usuarios segun sus puntos
     */
    public static void setRanking(ArrayList<String> ranking) {
        ControladorServidor.ranking = ranking;
    }
    /**
     * Muestran el estado de la partida
     * @return active true si está activo, false si no
     */
    public boolean isActive() {
        return active;
    }
    /**
     * Establece el estado de activación del servidor
     * @param active 
     */
    public void setActive(boolean active) {
        this.active = active;

    }
    /**
     * Obtien el hilo del servidor
     * @return t Hilo del servidor
     */
    public Thread getT() {
        return t;
    }
    /**
     * Método que muestra el menú con el listado de opciones a seleccionar
     */
    public static void mostrarMenu() {
        System.out.println("=== Menú de Opciones ===");
        System.out.println("1. Obtener listado de usuarios registrados del servidor");
        System.out.println("2. Obtener listado de usuarios en linea");
        System.out.println("3. Obtener listado de las partidas del servidor");
        System.out.println("4. Obtener Informacion de una partida concreta");
        System.out.println("0. Salir");
    }
    /**
     * Permite añadir un manejador a la lista
     * @param c Manejador del cliente
     */
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
    /**
     * Permite crear una partida entre dos usuarios
     * @param seleccionado Usuario que se selecciona
     * @param seleccionador Usuario que selecciona
     * @return p Partida
     */
    public static PaquetePartida creaPartida(String seleccionado, String seleccionador){
        PaquetePartida p = new PaquetePartida(seleccionado, seleccionador);
        ManejadorCliente c1 = getManejador(seleccionado);
        listaPartidas.add(p);
        c1.enviaPartida(p);
        return p;
    }
}
