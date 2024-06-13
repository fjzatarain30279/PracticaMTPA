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
 *
 * @author javier
 */
public class LectorFicheros {
    
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
    
    public static ArrayList<PaquetePartida> lecturaPartidas(String linea){
        ArrayList<PaquetePartida> listaPartidas = new ArrayList();

        
        return listaPartidas;
    }
    
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
