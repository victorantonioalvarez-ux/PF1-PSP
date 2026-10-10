/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.paucasesnoves;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author v
 */
public class FiltreLog {
    
    public static void main(String[] args){
        String paraulaClau = "ERROR";
        int comptador = 0;
        
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            String linia;
            while((linia = br.readLine()) != null){
                if(linia.contains(paraulaClau)){
                    comptador++;
                }
            }
            
        } catch (IOException ex) { 
            System.err.println("La lectura ha fallat"+ex);
            System.exit(1);
        }
        System.out.println(comptador);
        System.exit(0);
    }
    
}
