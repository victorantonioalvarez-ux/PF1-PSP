/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.paucasesnoves;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author v
 */
public class AnalitzadorPrincipal {
    
    public static void main(String[] args){
        
        String texto = "INFO Servidor iniciado\n"
                     + "ERROR Conexion perdida\n"
                     + "WARNING Disco al 85%\n"
                     + "Error Timeout\n"
                     + "INFO sesion cerrada";
        
        ProcessBuilder pb = new ProcessBuilder(
                "java",
                "-cp",
                System.getProperty("java.class.path"),
                "procesos.FiltreLog"
            );
        try{
            Process fill = pb.start();
        
        OutputStream out = fill.getOutputStream();
        out.write((texto).getBytes());
        out.flush();
        out.close();
        
        
        }catch (IOException e) {
            System.err.println("ERROR: de E/S: "+ e.getMessage());
        }
    }
}
