/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package a4ud1_alumno;

import CLASESDATOS.Alumno;
import CLASESDATOS.Nombre;
import CLASESDATOS.NotaAlumno;
import CLASESDATOS.NotaModulo;
import clases.Escritura;
import clases.FicheroRandom;
import clases.Lectura;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ruben
 */
public class Operaciones {

    final String SECUENCIAL = "NotasAlumnos.dat";
    final String ALEATORIO = "alumnos.dat";

    FicheroRandom rand;
    Lectura lectura;
    Escritura escritura;
    Scanner sc;

    public Operaciones() throws FileNotFoundException {
        sc = new Scanner(System.in);
        rand = new FicheroRandom(ALEATORIO);
        lectura = new Lectura(SECUENCIAL);
        escritura = new Escritura(SECUENCIAL);
        rand.abrir();
    }

    public void mostrarAlumnos() {

        try {
            for (int i = 0; i < rand.numRegistro(); i++) {
                rand.mostrarDatos(rand.leerArchivo(i));
            }
        } catch (IOException ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrarNotas() {
        lectura.abrir();
        try {
            for (int i = 0; i < rand.numRegistro(); i++) {
                Alumno a = rand.leerArchivo(i);
                int num = a.getNumero();
                NotaAlumno na = lectura.ObtenerNotas(num);
                System.out.println(rand.mostrarNombres(a)
                        + "\n" + lectura.mostrarDatos(na));
            }
        } catch (IOException ex) {

        }
        lectura.cerrar();
    }

    private void mostrarUnicoAlumno(int i) {

        Alumno a = rand.leerArchivo(i - 1);
        NotaAlumno na = lectura.ObtenerNotas(i - 1);
        System.out.println(rand.mostrarNombres(a));
        System.out.println(lectura.mostrarDatos(na));
    }

    public void mostrarAlumno() {
        System.out.println("Elige un codigo alumno: ");
        int numAlum = sc.nextInt();
        lectura.abrir();
        Alumno a = rand.leerArchivo(numAlum - 1);
        NotaAlumno na = lectura.ObtenerNotas(numAlum);
        System.out.println(rand.mostrarNombres(a));
        System.out.println(lectura.mostrarDatos(na));
        lectura.cerrar();
    }

    public Date conseguirFecha() {
        Date d = null;
        try {
            System.out.println("Escribe una fecha(dd/mm/aaaa): ");
            String s = sc.next();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
            d = sdf.parse(s);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        return d;
    }

    public void escribirAlumno() {

        ArrayList<String> tlfn = new ArrayList<>();
        try {
            int numReg = rand.numRegistro() + 1;
            Alumno a = new Alumno();
            a.setNumero(numReg);

            System.out.println("Nombre Del Alumno: ");
            String nom = sc.next();
            System.out.println("Primer Apellido: ");
            String ap1 = sc.next();
            System.out.println("Segundo Apellido: ");
            String ap2 = sc.next();
            a.setNombre(new Nombre(nom, ap1, ap2));
            a.setFechaNac(conseguirFecha());
            System.out.println("Cuantos telefonos Tienes: ");
            int num = sc.nextInt();
            for (int i = 0; i < num; i++) {
                System.out.println("Escribe el numero de telefono(" + i + 1 + "): ");
                String s = sc.next();
                while (s.length() != 9) {
                    System.out.println("El numero no tiene la longitud necesaria, Vuelva a intentarlo: ");
                    s = sc.next();
                }
                tlfn.add(s);
            }
            a.setTelefono(tlfn);

            rand.escribirArchivo(a, numReg);

            escritura.añadir();
            NotaAlumno na = añdirModulos(numReg);
            escritura.escribirArchivo(na);
            escritura.cerrar();

            lectura.abrir();
            mostrarUnicoAlumno(rand.numRegistro());
            lectura.cerrar();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public NotaAlumno añdirModulos(int codAlumno) {

        ArrayList<NotaModulo> mod = new ArrayList<>();
        String nom = "";
        while (!nom.equals("*")) {
            System.out.println("Escribe un nombre de Modulo(* para cancelar):");
            sc.nextLine();
            nom = sc.nextLine();
            if (!nom.equals("*")) {

                System.out.println("Escribe la nota: ");
                double num = sc.nextDouble();
                mod.add(new NotaModulo(nom, num));
            }
        }

        return new NotaAlumno(codAlumno, mod);

    }

    public void borrarOAgregarTelefonos() {
        System.out.println("Elige un codigo alumno: ");
        int numAlum = sc.nextInt();

        Alumno a = rand.leerArchivo(numAlum - 1);
        rand.mostrarDatos(a);
        ArrayList<String> lista = a.getTelefono();
        System.out.println("Escribe un numero de telefono:");
        String nTlfn = sc.next();
        int numVer = -1;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).equals(nTlfn)) {
                numVer = i;
            }
        }
        char op = 'S';
        if (numVer > 0) {
            System.out.println("El telefono " + nTlfn + " Existe,¿Quieres Borrarlo?(S para aceptar, pulsa cualquier tecla para cancelar):");
            op = sc.next().charAt(0);
            if (op == 'S') {
                lista.remove(numVer);
            }
        } else {
            System.out.println("El telefono " + nTlfn + " No Existe,¿Quieres Añadirlo?(S para aceptar, pulsa cualquier tecla para cancelar):");
            op = sc.next().charAt(0);
            if (op == 'S') {
                lista.add(nTlfn);
            }
        }

    }

    public void imprimirListado() {

        DateFormat date = DateFormat.getDateInstance();
        DecimalFormat dec = new DecimalFormat("#.#");
        String lista = "";
        try {
            PrintStream print = new PrintStream("Alumnos.txt");
            lista += "------------DATOS ALUMNOS-------------------";
            lectura.abrir();

            for (int i = 0; i < rand.numRegistro(); i++) {
                Alumno a = rand.leerArchivo(i);
                NotaAlumno na = lectura.ObtenerNotas(a.getNumero());
                lista += "\nALUMNO NUM: " + a.getNumero() + "\n"
                        + "NOMBRE: " + a.getNombre().getNombre() + " " + a.getNombre().getApellido1() + "_" + a.getNombre().getApellido2() + "\n"
                        + "FECHA NACIMIENTO: " + date.format(a.getFechaNac()) + "\t EDAD: " + calcularEdad(a.getFechaNac()) + "\n"
                        + "TELEFONO(S): ";
                for (String tlfn : a.getTelefono()) {
                    lista += tlfn + " ";
                }
                lista += "\n\nModulo\t\t\t\tNota\n----------------------------------------------\n";
                double numMedia = 0;
                for (NotaModulo m : na.getNotas()) {
                    numMedia += m.getNota();
                    lista += m.getAsignatura() + "\t\t\t" + m.getNota() + "\n";
                }
                lista += "....................\n"
                        + "NOTA MEDIA: \t\t\t" + dec.format(numMedia / na.getNotas().size()) + "\n"
                        + "....................................\n"
                        + "....................................\n"
                        + "--------------------------------------------------------------------------------------------";
            }

            lista += "\n\nTOTAL ALUMNOS........................." + rand.numRegistro();
            lectura.cerrar();
            print.print(lista);
        } catch (IOException ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static int calcularEdad(Date fechaNac) {
        Calendar fNac = Calendar.getInstance();
        Calendar fAct = Calendar.getInstance();

        fNac.setTime(fechaNac);
        int ano = fAct.get(Calendar.YEAR) - fNac.get(Calendar.YEAR);
        int mes = fAct.get(Calendar.MONTH) - fNac.get(Calendar.MONTH);
        int dia = fAct.get(Calendar.DATE) - fNac.get(Calendar.DATE);

        if (mes < 0 || (mes == 0 && dia < 0)) {
            ano--;
        }

        return ano;

    }

    public void cerrarDirecto() {
        rand.cerrar();
    }

}
