/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 *
 * @author ruben
 */
public class MyObjectOutputStream extends ObjectOutputStream {
    
    public MyObjectOutputStream(OutputStream out)throws IOException{
        super(out);
    }
    
    public MyObjectOutputStream()throws IOException, SecurityException{
        super();
    } 

    @Override
    protected void writeStreamHeader() throws IOException {
        
    }
    
    
    
}
