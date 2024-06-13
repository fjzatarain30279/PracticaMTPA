package com.mycompany.grupo9;

//--> El cliente que se conecta al servidor
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import com.mycompany.grupo9.paquetes.PaqueteLogin;
import com.mycompany.grupo9.paquetes.PaquetePartida;

public class ManejadorCliente
        extends Thread {

    private Socket cliente; //--> Canal de Lectura y Escritura
    private OutputStream os;
    private InputStream is;

    public ManejadorCliente(Socket sck) throws Exception {
        cliente = sck;
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
            setName(p.getUsuario());
            ControladorServidor.addManejador(this);

        }
        return p;
    }

}
