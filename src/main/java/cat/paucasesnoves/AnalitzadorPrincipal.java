/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.paucasesnoves;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

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
        
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
            out.write(texto);
            out.flush();
            out.close();
            try(BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()))){
                String resposta = in.readLine();
                IO.println("Errores encontrados: "+ resposta);
            }
            
            p.waitFor();
            
        }catch (IOException e) {
            System.err.println("ERROR: de E/S: "+ e.getMessage());
        } catch (InterruptedException ex) {
            System.getLogger(AnalitzadorPrincipal.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
