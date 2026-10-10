/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.paucasesnoves;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
            Process p = pb.start();
        
            OutputStream out = p.getOutputStream();
            out.write((texto).getBytes());
            out.flush();
            out.close();
            BufferedReader entrada = new BufferedReader(new InputStreamReader(p.getInputStream()));
            p.waitFor();
            
        }catch (IOException e) {
            System.err.println("ERROR: de E/S: "+ e.getMessage());
        } catch (InterruptedException ex) {
            System.getLogger(AnalitzadorPrincipal.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
