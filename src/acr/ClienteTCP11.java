/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acr;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class ClienteTCP11 {

       Socket cliente;
    InputStream entrada;
    OutputStream salida;
    DataInputStream datosentrada;
    DataOutputStream datossalida;
    Scanner scan;
    public ClienteTCP11() {
   try {
            cliente = new Socket("127.0.0.1", 1025);
            entrada = cliente.getInputStream();
            salida = cliente.getOutputStream();
            datosentrada = new DataInputStream(entrada);
            datossalida = new DataOutputStream(salida);
            scan = new Scanner(System.in);// va a leer desde teclado

            // ENVIAR MENSAJE AL SERVIDOR
            //leer desde el teclado lo que el usuario puso
            //enviar lo que se leyo
        

                // RECIBIR EL ENTERO LARGO
                System.out.println("dato: " + datosentrada.readUTF());
                cliente.close();
            }catch(UnknownHostException ex){
            ex.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static void main(String args[]){
       ClienteTCP11 c1 = new ClienteTCP11();


    }
}
