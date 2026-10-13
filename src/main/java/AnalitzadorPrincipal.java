/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.File;

/**
 *
 * @author v
 */
public class AnalitzadorPrincipal {

    public static void main(String[] args) {

        String text = "INFO Servidor iniciat\n"
                + "WARNING Disco al 95%\n"
                + "ERROR Conexio perduda\n"
                + "WARNING Disco al 90%\n"
                + "ERROR Timeout\n"
                + "WARNING Disco al 85%\n"
                + "INFO sesio tancada";
               
        ProcessBuilder pb = new ProcessBuilder(
                "java",
                "-cp",
                System.getProperty("java.class.path"),
                "FiltreLog"
        ); 
        
        try {
            
            Process perr = llancarFiltre("ERROR");
            Process pwrn = llancarFiltre("WARNING");
            
            enviarText(perr, text);
            enviarText(pwrn, text);

            try (BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()))) {

                String resposta = in.readLine();
                if(resposta != null){
                    System.out.println("Errors trobats: " + resposta);
                }else{
                    System.out.println("No s'ha rebut cap resultat del fill");
                }
                
            }
            p.waitFor();
            int codi = p.exitValue();
            if(codi == 0){
                System.out.println("El fill ha finalitzat sense errors");
            }else{
                System.out.println("El fill ha finalitzat amb errors");
            }
        } catch (IOException e) {
            System.err.println("ERROR: de E/S: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("proces interromput");
        }
    }
    
    private static Process llancarFiltre(String paraula) throws IOException{
        ProcessBuilder pb = new ProcessBuilder(
                "java",
                "-cp",
                System.getProperty("java.class.path"),
                "FiltreLog", paraula
        );
        pb.redirectError(ProcessBuilder.Redirect.appendTo(new File("errors_filtre.log")));
        return pb.start();
    }
    private static void enviarText(Process p, String text) throws IOException{
        try(BufferedWriter out = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()))){
            out.write(text);
            out.flush();
            out.close();
        }
    }
}
