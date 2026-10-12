/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author v
 */
public class FiltreLog {

    public static void main(String[] args) {
        String paraulaClau = (args.length > 0) ? args[0] : "ERROR";
        int comptador = 0;
        boolean hiHaContingut = false;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String linia;
            while ((linia = br.readLine()) != null) {
                if (!linia.isBlank()) {
                    hiHaContingut = true;
                }
                if (linia.contains(paraulaClau)) {
                    comptador++;
                }

            }

        } catch (IOException ex) {
            System.err.println("La lectura ha fallat: " + ex);
            System.exit(1);
        }
        if (hiHaContingut == false) {
            System.err.println("Error: Text buit");
            System.exit(1);
        }
        System.out.println(comptador);
        System.exit(0);
    }

}
