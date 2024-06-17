package com.mycompany.grupo9;

//--> El cliente que se conecta al servidor
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import com.mycompany.grupo9.paquetes.PaqueteLogin;
import com.mycompany.grupo9.paquetes.PaquetePartida;
import com.mycompany.grupo9.paquetes.PaqueteUsr;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Clase que implementa el manejador del cliente
 *
 * @author Javier Zataraín
 * @authro Blanca Jorge
 */
public class ManejadorCliente
        extends Thread {

    private Socket cliente; //--> Canal de Lectura y Escritura
    private OutputStream os;
    private InputStream is;
    private boolean enPartida;

    /**
     * Constructor de la clase
     *
     * @param sck el socket del cliente
     * @throws Exception Si ocurre algún error al iniciar el cliente
     */
    public ManejadorCliente(Socket sck) throws Exception {
        cliente = sck;
        enPartida = false;
        start();
    }

    /**
     * Método que se ejecuta al iniciar los hilos
     */
    public void run() {

        try {
            java.io.BufferedReader inred = new java.io.BufferedReader(new java.io.InputStreamReader(cliente.getInputStream()));
            java.io.PrintStream outred = new java.io.PrintStream(cliente.getOutputStream());
            String linea;

            boolean correcto = false;
            while (((linea = inred.readLine()) != null) && (correcto == false)) {
                if (linea.charAt(0) == 'L') {
                    outred.println(gestionLogin(linea.substring(1)).toString());
                    outred.flush();
                } else if (linea.charAt(0) == 'A') {
                    outred.println(conectados());
                    outred.flush();
                } else if (linea.charAt(0) == 'U') {
                    PaqueteUsr p = gestionUsr(linea.substring(1));
                    PaquetePartida partida = ControladorServidor.creaPartida(p.getSeleccionado(), p.getSeleccionador());
                    outred.println("P");
                    outred.flush();

                } else if (linea.charAt(0) == 'P') {
                    System.out.println(linea);
                    outred.println(gestionPartida(linea.substring(1)).toString());
                    outred.flush();
                } else if (linea.charAt(0) == 'T') {
                    PaquetePartida p = ControladorServidor.buscaTablero(linea.split(";")[1]);
                    outred.println(p.toString());
                    outred.flush();
                } else if (linea.charAt(0) == 'X') {
                    cliente.close();
                    ControladorServidor.removeManejador(this);
                }

            }
        } catch (java.io.IOException ioe) {
            System.err.println("Cerrando socket de cliente");
        }

    }

    /**
     * Envía un mensaje al cliente
     *
     * @param mensaje Lo que se quiera transmitir
     * @throws Exception Si ocurre un error al enviar el mensaje
     */
    public void sendMessage(byte[] mensaje) throws Exception {
        System.out.println("Haciendo difusion...");
        os.write(mensaje);
    }

    /**
     * Gestiona una partida a partir de una línea de texto.
     *
     * @param linea La línea de texto que contiene la información de la partida
     * @return Un objeto PaquetePartida que representa la partida gestionada
     */
    public PaquetePartida gestionPartida(String linea) {
        DecodificadorPartida dec = new DecodificadorPartida();
        PaquetePartida p;
        p = dec.decodificar(linea);
        System.out.println(p.toString());
        if (!compruebaJugada(p.getTablero(), p.getMovimiento())) {
            return p;
        } else {
            p = hacerJugada(p);
                    System.out.println(p.toString());

            ControladorServidor.modificaTablero(p);
            if (compruebaTablero(p.getTablero())) {
                p.setGanador(p.getTurno());
                p.setFinalizada(true);
                ControladorServidor.partidaFinalizada(p);
            }
            return p;
        }
    }
    public void enviaPartida(PaquetePartida p){
        try {
            PrintStream outred = new java.io.PrintStream(cliente.getOutputStream());
            outred.println(p.toString());
        } catch (IOException ex) {
        }
    }

    /**
     * Envía la creacion de partida al cliente.
     */
    public void enviaCrearPartida() {
        try {
            PrintStream outred = new java.io.PrintStream(cliente.getOutputStream());
            outred.println("P");
        } catch (IOException ex) {
        }
    }

    /**
     * Gestiona la información del usuario.
     *
     * @param linea La línea de texto que contiene la información del usuario
     * @return Un objeto PaqueteUsr que representa el usuario gestionado
     */
    public PaqueteUsr gestionUsr(String linea) {
        DecodificadorUsr dec = new DecodificadorUsr();
        PaqueteUsr p;
        p = dec.decodificar(linea);
        return p;
    }

    /**
     * Gestiona la información del inicio de sesión
     *
     * @param linea Línea que contiene las credenciales
     * @return p El inicio de sesión
     */
    public PaqueteLogin gestionLogin(String linea) {
        DecodificadorLogin dec = new DecodificadorLogin();
        PaqueteLogin p;
        p = dec.decodificar(linea);
        if (p.getComprobado() == -1) {
            System.out.println("CREANDO USR");
            if (ControladorServidor.compruebaCrear(p.getUsuario(), p.getPassword()) != 1) {
                p.setComprobado(1);
                System.out.println("USR ya existente");

            } else {
                ControladorServidor.creaUsr(p.getUsuario(), p.getPassword());
                p.setComprobado(3);
                System.out.println("Creacion Correcta");
                setName(p.getUsuario());
                ControladorServidor.addManejador(this);
            }
        } else {
            p.setComprobado(ControladorServidor.compruebaLogin(p.getUsuario(), p.getPassword()));
            System.out.println("Comprobacion de usuario y contraseña");
            if (p.getComprobado() == 3) {
                setName(p.getUsuario());
                ControladorServidor.addManejador(this);
            }
        }
        return p;
    }

    /**
     * Obtiene la lista de usuarios conectados
     *
     * @return listado Lista de usuarios
     */
    public String conectados() {
        String listado = "";
        for (ManejadorCliente m : ControladorServidor.getListaManejadores()) {
            listado = listado + m.getName() + ";";
        }
        return listado;
    }
    public boolean isLleno(char[][] board){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board[i][j] != 'X' && board[i][j] != 'O' ){
                    return false;
                }
            }
        }
        return true;
    }
    public boolean compruebaTablero(char[][] board) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && (board[i][0] == 'X'||board[i][0] == 'O')) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && (board[i][0] == 'X'||board[i][0] == 'O')) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]  && (board[0][0] == 'X'||board[0][0] == 'O')) {
            return true;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && (board[0][2] == 'X'||board[0][2] == 'O')) {
            return true;
        }

        return false;
    }

    public boolean compruebaJugada(char[][] tablero, int[] jugada) {
        if (tablero[jugada[0]][jugada[1]] == 'X' || tablero[jugada[0]][jugada[1]] == 'O') {
            return false;
        }
        return true;
    }

    public PaquetePartida hacerJugada(PaquetePartida p) {
        if (p.getTurno().equalsIgnoreCase(p.getJug1())) {
            p.getTablero()[p.getMovimiento()[0]][p.getMovimiento()[1]] = 'X';
            p.setTurno(p.getJug2());
        } else {
            p.getTablero()[p.getMovimiento()[0]][p.getMovimiento()[1]] = 'O';
            p.setTurno(p.getJug1());
        }
        return p;
    }
}
