/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarellitas;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 *
 * @author Dell
 */
public class prints {
    public static void main(String[] args) {
 
        Scanner sc = new Scanner(System.in);
        String cadena;
  
        try (FileWriter fw = new FileWriter("C:\\Users\\Dell\\Desktop\\Memoria\\5to semestre\\Patrones\\Mis DatSets\\AND.txt", true); //try with resources
             PrintWriter salida = new PrintWriter(fw)) {
             System.out.println("Introduce texto. Para acabar introduce la cadena FIN:");                         
             cadena = sc.nextLine();
             while (!cadena.equalsIgnoreCase("FIN")) {
                     salida.println(cadena);
                     cadena = sc.nextLine();
             }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());                                                                  
        }
    }
    
}

