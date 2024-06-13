/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cliente.modelo;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author javier
 */
public class PaqueteUsr extends AbstractListModel{
    private ArrayList<String> listaUsuarios;
    private String seleccionado;
    private String seleccionador;

    public String getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(String seleccionado) {
        this.seleccionado = seleccionado;
    }

    public String getSeleccionador() {
        return seleccionador;
    }

    public void setSeleccionador(String seleccionador) {
        this.seleccionador = seleccionador;
    }

    public ArrayList<String> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<String> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
    public PaqueteUsr(){
        listaUsuarios = new ArrayList<String>();
        seleccionado = "";
        seleccionador = "";
    }
    
    @Override
    public int getSize() {
       return listaUsuarios.size();
    }
    
    @Override
    public String getElementAt(int indice) {
      String usr = listaUsuarios.get(indice);
      return usr;
    }
    
    @Override
    public String toString(){
        String linea = "";
        for(String s:listaUsuarios){
            linea = linea + s + ";";
        }
        linea = linea + "-" + seleccionado +  ";" + seleccionador;
        return linea;
    }
    
}
