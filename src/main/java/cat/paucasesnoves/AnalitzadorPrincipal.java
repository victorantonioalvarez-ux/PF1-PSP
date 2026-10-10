/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.paucasesnoves;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 *
 * @author v
 */
public class AnalitzadorPrincipal {

    public static void main(String[] args) {

        String texto = "INFO Servidor iniciat\n"
                + "ERROR Conexio perduda\n"
                + "WARNING Disco al 85%\n"
                + "ERROR Timeout\n"
                + "INFO sesio tancada";

        ProcessBuilder pb = new ProcessBuilder(
                "java",
                "-cp",
                System.getProperty("java.class.path"),
                "cat.paucasesnoves.FiltreLog"
        );
        
        try {
            Process p = pb.start();

            try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()))) {

                out.write(texto);
                out.flush();
                out.close();
            }

            try (BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()))) {

                String resposta = in.readLine();
                System.out.println("Errors trobats: " + resposta);
            }
            int codi = p.waitFor();
            System.out.println("El fill ha finalitzat amb el codi: " + codi);
        } catch (IOException e) {
            System.err.println("ERROR: de E/S: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("proces interromput");
        }
    }
}
