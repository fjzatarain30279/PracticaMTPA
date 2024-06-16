/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupo9;

import com.mycompany.grupo9.paquetes.PaquetePartida;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clase encargada de la lectura y el guardado de información en los ficheros
 * @author Javier Zataraín
 * @author Blanca Jorge
 */
public class LectorFicheros {
    /**
     * Lee el archivo de los usuarios y devuleve un hashmap con los nombres y las
     * contraseñas de estos
     * @param linea Nombre del archivo donde se incluyen estos datos
     * @return listado HashMap con el nombre de usuario y las contraseñas
     */
    public static HashMap<String,String> lecturaFichero(String linea){
       HashMap<String,String> listado = new HashMap();
       try (BufferedReader br = new BufferedReader(new FileReader(linea))) {
            String aux;
            while ((aux = br.readLine()) != null) {
                listado.put(aux.split(";")[0], aux.split(";")[1]);
            }
        } catch (IOException e) {
            System.out.println("Fichero de usuarios no encontrado...");
        }
       
       return listado;
    }
    /**
     * Lee el archivo que incluye las partidas
     * @param linea Nombre del archivo que contine las partidas
     * @return listaPartidas Listado de las partidas
     */
    public static ArrayList<PaquetePartida> lecturaPartidas(String linea){
        ArrayList<PaquetePartida> listaPartidas = new ArrayList();

        
        return listaPartidas;
    }
    /**
     * Lee el archivo de las puntuaciones
     * @param linea Nombre del archivo que contiene las puntuaciones
     * @return ranking Las puntuaciones
     */
    public static ArrayList<String> lecturaPuntuaciones(String linea){
        ArrayList<String> ranking = new ArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(linea))) {
            String aux;
            while ((aux = br.readLine()) != null) {
                ranking.add(aux);
            }
        } catch (IOException e) {
            System.out.println("Fichero de puntuaciones no encontrado...");
        }
        return ranking;
    }
    /**
     * Guarda las puntuaciones actualizadas en el archivo correspondiente
     * @param linea Nombre del archivo de la puntuaciones
     * @param array Lista de cadenas que representan las puntuaciones
     */
    public static void guardaPuntuaciones(String linea, ArrayList<String> array){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(linea))) {
            for (String line : array) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            System.out.println("Archivo escrito exitosamente.");
            } catch (IOException e) {
                System.out.println("\nError en el guardado de Puntuaciones...");
        }
    }
    /**
     * Guarda los usuarios y las contraseñas
     * @param linea Nombre del archivo de los usuarios
     * @param usuarios HashMap con los nombres y contraseñas de los usuarios
     */
    public static void guardaUsr(String linea, HashMap<String,String> usuarios){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(linea))) {
            for (String usr : usuarios.keySet()) {
               String line = usr + ";" + usuarios.get(usr);
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            System.out.println("Archivo escrito exitosamente.");
            } catch (IOException e) {
                System.out.println("\nError en el guardado de Usuarios...");
        }
    }
    /**
     * Guarda en el archivo las plantillas
     * @param linea Nombre del archivo donde se guardan las partidas
     * @param array Lista que representa las partidas
     */
    public static void guardaPartidas(String linea, ArrayList<PaquetePartida> array){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(linea))) {
            for (PaquetePartida partida : array) {
                bufferedWriter.write(partida.toString());
                bufferedWriter.newLine();
            }
            System.out.println("Archivo escrito exitosamente.");
            } catch (IOException e) {
                System.out.println("\nError en el guardado de Partidas...");
        }
    }
    
}
