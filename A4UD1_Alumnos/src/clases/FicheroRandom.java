/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import CLASESDATOS.Alumno;
import CLASESDATOS.Nombre;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class FicheroRandom extends Archivo {

    DateFormat formatD = DateFormat.getInstance();
    RandomAccessFile rand;
    int numReg = 0;
    final long TAMAÑO = 100;

    public FicheroRandom(String file) {
        super(file);
    }

    @Override
    public void abrir() {
        try {
            rand = new RandomAccessFile(archivo, "rw");
        } catch (FileNotFoundException ex) {

        }
    }

    @Override
    public void cerrar() {
        try {
            rand.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int numRegistro() throws IOException {
        return (int) Math.ceil((double) rand.length() / TAMAÑO);
    }

    public void escribirArchivo(Alumno a, int nreg) {
        try {
            if (combrobarLongitud(a)) {
                rand.seek(nreg * TAMAÑO);
                rand.writeInt(a.getNumero());
                rand.writeUTF(a.getNombre().getNombre());
                rand.writeUTF(a.getNombre().getApellido1());
                rand.writeUTF(a.getNombre().getApellido2());
                rand.writeLong(a.getFechaNac().getTime());
                rand.writeInt(a.getTelefono().size());
                for (String tlfn : a.getTelefono()) {
                    rand.writeUTF(tlfn);
                }
                rand.writeBoolean(a.isBorrado());
                
            } else {
                throw new PasadoException();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (PasadoException ex) {
            System.out.println("Te has pasado de longitud, el archivo no lo aguanta");
        }

    }

    public Alumno leerArchivo(int nreg) {
        Alumno a = new Alumno();
        ArrayList<String> numT = new ArrayList<>();
        try {
            rand.seek(nreg * TAMAÑO);
            a.setNumero(rand.readInt());
            a.setNombre(new Nombre(rand.readUTF(), rand.readUTF(), rand.readUTF()));
            a.setFechaNac(new Date(rand.readLong()));
            int numTlfn = rand.readInt();
            for (int i = 0; i < numTlfn; i++) {
                numT.add(rand.readUTF());
            }
            a.setTelefono(numT);
            a.setBorrado(rand.readBoolean());

        } catch (IOException ex) {
            Logger.getLogger(FicheroRandom.class.getName()).log(Level.SEVERE, null, ex);
        }

        return a;
    }

    public void mostrarDatos(Alumno a) {
        String muestra = "";
        muestra += a.getNumero() + "º " + a.getNombre().getNombre() + " " + a.getNombre().getApellido1() + " " + a.getNombre().getApellido2() + "\n"
                + "fecha Nacimiento:" + formatD.format(a.getFechaNac()) + " Telefono/s: ";
        for (String nums : a.getTelefono()) {
            muestra += nums + " ";
        }
        System.out.println(muestra + "\n");

    }
    
    public String mostrarNombres(Alumno a){
        String muestra ="";
        muestra += a.getNumero() + "º "+ a.getNombre().getNombre() + " " + a.getNombre().getApellido1() + " " + a.getNombre().getApellido2();
        return muestra;
    }

    public boolean combrobarLongitud(Alumno a) {
        return (4 + a.getNombre().getNombre().length()
                + a.getNombre().getApellido1().length()
                + a.getNombre().getApellido2().length()
                + 12 + 4 + a.getTelefono().size() * 9) < TAMAÑO;
    }

}
