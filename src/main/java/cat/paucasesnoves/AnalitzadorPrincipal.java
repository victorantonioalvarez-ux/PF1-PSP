/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.paucasesnoves;

/**
 *
 * @author v
 */
public class AnalitzadorPrincipal {
    
    public static void main(String[] args){
        
        ProcessBuilder pb = new ProcessBuilder(
                "java",
                "-cp",
                System.getProperty("java.class.path"),
                "procesos.FiltreLog"
            );
        
    }
}
