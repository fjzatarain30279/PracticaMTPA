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
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManejadorCliente
        extends Thread {

    private Socket cliente; //--> Canal de Lectura y Escritura
    private OutputStream os;
    private InputStream is;
    private boolean enPartida;

    public ManejadorCliente(Socket sck) throws Exception {
        cliente = sck;
        enPartida = false;
        start();
    }

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
                    outred.println(gestionPartida(linea.substring(1)).toString());
                    outred.flush();
                }else if (linea.charAt(0) == 'T') {
                    PaquetePartida p = ControladorServidor.buscaTablero(linea.split(";")[1]);
                    outred.println(p.toString());
                    outred.flush();
                }

            }
        } catch (java.io.IOException ioe) {
            System.err.println("Cerrando socket de cliente");
            ioe.printStackTrace(System.err);
        }

    }

    public void sendMessage(byte[] mensaje) throws Exception {
        System.out.println("Haciendo difusion...");
        os.write(mensaje);
    }

    public PaquetePartida gestionPartida(String linea) {
        DecodificadorPartida dec = new DecodificadorPartida();
        PaquetePartida p;
        p = dec.decodificar(linea);

        return p;
    }
    public void enviaPartida(PaquetePartida p){
        try {
            PrintStream outred = new java.io.PrintStream(cliente.getOutputStream());
            outred.println("P");
        } catch (IOException ex) {
        }
    }

    public PaqueteUsr gestionUsr(String linea) {
        DecodificadorUsr dec = new DecodificadorUsr();
        PaqueteUsr p;
        p = dec.decodificar(linea);
        return p;
    }

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

    public String conectados() {
        String listado = "";
        for (ManejadorCliente m : ControladorServidor.getListaManejadores()) {
            listado = listado + m.getName() + ";";
        }
        return listado;
    }
}
