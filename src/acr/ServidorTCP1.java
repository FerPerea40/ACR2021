/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acr;

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
    OutputStream salida;
    InputStream entrada;

    public ServidorTCP1() {
        try {
            servidor = new ServerSocket(1025);//puertos 1-65535 . 1-1024 reservados
            cliente = servidor.accept();//aceptar y asignar un socket exclusivo para el cliente
            //establecer flujos de entrada y salida
            salida = cliente.getOutputStream();
            entrada = cliente.getInputStream();
            //mandar mensaje del cliente
            salida.write(100);// este mensaje es para el cliente
            //Recibir el mensaje cel cliente
            byte mensaje[] = new byte[15];
            int cb = entrada.read(mensaje);
            System.out.println("Enteros: "+cb);   
            String msg = new String(mensaje);
            System.out.println(msg);
             salida.write(mensaje);
             // el mensaje del cliente se devuelva tal cual al mismo cliente
cliente.close();
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
