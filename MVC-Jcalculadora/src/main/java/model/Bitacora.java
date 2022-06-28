/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author bryam
 */
public class Bitacora {
    private String bitacora;
    private final String path;

    public Bitacora() {
        this.bitacora = "";
        this.path = "src\\main\\java\\model\\Bitacora.txt";
    }

    public String leerBitacora(){
        this.bitacora = "";
        try{
            BufferedReader bf = new BufferedReader(new FileReader(this.path));
            String lector;
            while((lector = bf.readLine()) != null){             
                bitacora+= "  " + lector + "\n"; 
            }
            bf.close();
            
        }catch(IOException e){
            System.err.println("No se encontró el TXT con la bitácora");
        }
        
        return this.bitacora;
    }
    
    public void escribirEnBitacora(String line){
        try{
            FileWriter fw = new FileWriter(this.path, true);    
            
            fw.write((line + "\n"));
            fw.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        
    }
    
}
