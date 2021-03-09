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
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Dell
 */
public class ServidorTCP1 {

   ServerSocket servidor;
    Socket cliente;
    Socket cliente2;

    InputStream entrada;

    OutputStream salida;
    DataInputStream datosentrada;
    DataOutputStream datossalida;
    InputStream entrada2;

    OutputStream salida2;
    DataInputStream datosentrada2;
    DataOutputStream datossalida2;
String msj;

    public ServidorTCP1() {
        try {
               servidor = new ServerSocket(1025);
       
                cliente = servidor.accept();
                System.out.println("El cliente: "+ cliente.getInetAddress().getHostAddress()+" en el puerto: "+ cliente.getPort());
                entrada = cliente.getInputStream();
                salida = cliente.getOutputStream();
                datosentrada = new DataInputStream(entrada);
                datossalida = new DataOutputStream(salida);
                // RECIBE MENSAJE Y LO MUESTRA
                
                    String msg = datosentrada.readUTF();
                    System.out.println("El cliente 1 dice> " + msg);
                    msj = msg;
                    cliente.close();
                      cliente2 = servidor.accept();
                           entrada2 = cliente2.getInputStream();
                salida2 = cliente2.getOutputStream();
                datosentrada2 = new DataInputStream(entrada2);
                datossalida2 = new DataOutputStream(salida2);            
                    
                    datossalida2.writeUTF("Enviaste:"+msj);

            
                
cliente2.close();
            

          
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServidorTCP1 s1 = new ServidorTCP1();
    }

}
