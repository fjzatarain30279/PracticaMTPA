/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupo9.paquetes;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author javier
 */
public class PaqueteUsr extends AbstractListModel{
    private ArrayList<String> listaUsuarios;

    public ArrayList<String> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<String> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
    public PaqueteUsr(){
        listaUsuarios = new ArrayList<String>();
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
    
}
