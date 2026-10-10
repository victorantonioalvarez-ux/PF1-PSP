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
        String palabraClave = "ERROR";
        int comptador = 0;
        
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            String linea;
            while((linea = br.readLine()) != null){
                if(linea.contains(palabraClave)){
                    comptador++;
                }
            }
            
        } catch (IOException e) {
            System.err.println("ERROR: text buit");
            System.exit(1);
        }
        IO.print(comptador);
    }
    
}
