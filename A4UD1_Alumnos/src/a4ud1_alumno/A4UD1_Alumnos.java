/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a4ud1_alumno;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class A4UD1_Alumnos {

    static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {

        try {
            Operaciones o = new Operaciones();
            boolean salir = false;
            do{
            
            Scanner sc = new Scanner(System.in);
            System.out.println("-------Sistema Educativo Ver 0.4----------\n"
                    + "1.- Listar Alumnos\n"
                    + "2.- Listar Alumnos Y modulos\n"
                    + "3.- Registrar Nuevo Alumno y sus modulos\n"
                    + "4.- Visualizar un alumno\n"
                    + "5.- Eliminar o Añádir Telefono\n"
                    + "6.- Imprimir listado\n"
                    + "7.- SALIR\n"
                    + "Elige una opcion: ");
            int num = sc.nextInt();
            int cod = 0;

            switch(num){
                case 1:
                    o.mostrarAlumnos();
                    break;
                case 2:
                    o.mostrarNotas();
                    break;
                case 3:
                    o.escribirAlumno();
                    break;
                case 4:
                    o.mostrarAlumno();
                    break;
                case 5:
                    o.borrarOAgregarTelefonos();
                    break;
                case 6:
                    o.imprimirListado();
                    break;
                case 7:
                    System.out.println("Hasta Otra");
                    salir = true;
                    break;
                default:
                    System.out.println("Orden no valida, vuelva a intentarlo");
                    break;
            }
            }while(!salir);
            o.cerrarDirecto();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
