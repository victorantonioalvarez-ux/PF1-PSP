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
                + "ERROR Conexio perduda\n"
                + "WARNING Disco al 85%\n"
                + "INFO sesio tancada";

        try {

            Process perr = llancarFiltre("ERROR");
            Process pwrn = llancarFiltre("WARNING");

            enviarText(perr, text);
            enviarText(pwrn, text);

            String resposta1 = llegirResultat(perr);
            String resposta2 = llegirResultat(pwrn);

            perr.waitFor();
            pwrn.waitFor();

            int codi1 = perr.exitValue();
            int codi2 = pwrn.exitValue();

            int codiFinal;
            if (codi1 != 0 || codi2 != 0) {
                codiFinal = 1;
            } else {
                codiFinal = 0;
            }

            System.out.println("RESULTAT: ERRORS=" + resposta1
                    + " | WARNINGS=" + resposta2
                    + " | EXIT_CODE=" + codiFinal);
        } catch (IOException e) {
            System.err.println("ERROR: de E/S: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("proces interromput");
        }
    }

    private static Process llancarFiltre(String paraula) throws IOException {
        ProcessBuilder pb = new ProcessBuilder(
                "java",
                "-cp",
                System.getProperty("java.class.path"),
                "FiltreLog", paraula
        );
        pb.redirectError(ProcessBuilder.Redirect.appendTo(new File("errors_filtre.log")));
        return pb.start();
    }

    private static void enviarText(Process p, String text) throws IOException {
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()))) {
            out.write(text);
            out.flush();
            out.close();
        }
    }

    private static String llegirResultat(Process p) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()))) {

            return in.readLine();
        }
    }
}
