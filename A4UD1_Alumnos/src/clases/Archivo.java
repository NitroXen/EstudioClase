/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.io.File;

/**
 *
 * @author usuario
 */
public abstract class Archivo {
    File archivo;
    
    public Archivo(String file){
        archivo = new File(file);
    }
    
    public abstract void abrir();
    public abstract void cerrar();
    
    public boolean existe(){
        return archivo.exists();
    }
    
    public File getArchivo(){
        return archivo;
    }
    
    
}
