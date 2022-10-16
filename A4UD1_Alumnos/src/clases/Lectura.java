/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import CLASESDATOS.Alumno;
import CLASESDATOS.NotaAlumno;
import CLASESDATOS.NotaModulo;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Lectura extends Archivo {

    ObjectInputStream lectura;

    public Lectura(String name) {
        super(name);
    }

    @Override
    public void abrir() {
        try {
            lectura = new ObjectInputStream(new BufferedInputStream(new FileInputStream(archivo)));
        } catch (IOException ex) {
            Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void cerrar() {
        try {
            lectura.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void mostrarTodosDatos(){
        abrir();
        try{
            NotaAlumno na = (NotaAlumno) lectura.readObject();
            mostrarDatos(na);
        }catch(IOException ex){
            
        } catch (ClassNotFoundException ex) {
            
        }
        
        cerrar();
    }
    
    

    public NotaAlumno ObtenerNotas(int num) {
        NotaAlumno a = null;
        boolean salir = false;
        try {
            while (!salir) {
                a = (NotaAlumno) lectura.readObject();

                if (a.getNumero() == num) {
                    salir = true;
                }

            }
        } catch (IOException ex) {
            a = null;
        } catch (ClassNotFoundException ex) {
            
        }
        
        return a;
    }

    public String mostrarDatos(NotaAlumno a) {

        String dato = "";

        dato += "Notas: \n";

        for (NotaModulo num : a.getNotas()) {
            dato += num.getAsignatura() + " = " + num.getNota() + "\n";
        }
        return dato;

    }

}
