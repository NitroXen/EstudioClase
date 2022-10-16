/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import CLASESDATOS.NotaAlumno;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Escritura extends Archivo {

    ObjectOutputStream escritura;
    
    public Escritura(String name){
        super(name);
    }
    
    @Override
    public void abrir() {
        try{
            escritura = new ObjectOutputStream(new FileOutputStream(archivo, true));
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void añadir(){
        try {
            escritura = new MyObjectOutputStream(new BufferedOutputStream(new FileOutputStream(archivo)));
        } catch (IOException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void cerrar() {
        try{
            escritura.close();
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void escribirArchivo(NotaAlumno na){    
        try {
            escritura.writeObject(na);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
