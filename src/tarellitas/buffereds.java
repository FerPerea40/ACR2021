/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarellitas;

//Sample 01: Package inclusion
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
/**
 *
 * @author Dell
 */
public class buffereds {
    public static void main(String[] args) {
        try
        {
            //Sample 01: Open the FileWriter, Buffered Writer
            FileWriter fw = new
                    FileWriter("C:\\Temp\\TestFile.Txt");
            BufferedWriter WriteFileBuffer = new
                    BufferedWriter(fw);
            //Sample 02: Write Some Text to File
            // Using Buffered Writer)
            WriteFileBuffer.write("First Line");
            WriteFileBuffer.newLine();
            WriteFileBuffer.write("Second Line");
            WriteFileBuffer.newLine();
            WriteFileBuffer.write("Third Line");
            WriteFileBuffer.newLine();
            //Sample 03: Close both the Writers
            WriteFileBuffer.close();
            //Sample 04: Open the Readers Now
            FileReader fr = new 
                    FileReader("C:\\Temp\\TestFile.txt");
            BufferedReader ReadFileBuffer = new 
                    BufferedReader(fr);
            //Sample 05: Read the text Written 
            // using BufferedWriter
            System.out.println(ReadFileBuffer.readLine());
            System.out.println(ReadFileBuffer.readLine());
            System.out.println(ReadFileBuffer.readLine());
            //Sample 06: Close the Readers
            ReadFileBuffer.close();
        } catch (IOException Ex)
        {
            System.out.println(Ex.getMessage());
        }
    }
}



    
