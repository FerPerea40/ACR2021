/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica01;

import ejercicio3.ServidorArchivos;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *José Fernando Perea Macías
 * @author Dell
 * Socket -> Envio de Archivos
 * El programa consiste en una Servidor y un Cliente, donde por medio de sockets
 * podemos enviar archivos de diferente extensión, desde el cliente hacia el 
 * servidor finalmente el servidior se encarga de guardarlo con un identificador
 * mpas el nombre del archivo y su respectiva extensión.
 * 04/03/2021
 */
public class ServidorArchivo {
    ServerSocket servidor;
    Socket cliente;
    InputStream entrada;
    OutputStream salida;
    DataInputStream datosentrada;
    DataOutputStream datossalida;

    FileOutputStream escribirarchivo;
    
    public ServidorArchivo(){
        try{
        servidor = new ServerSocket(1027);
        System.out.println("El servidor está a la escucha en el puerto 1027");
        cliente= servidor.accept();
        entrada =  cliente.getInputStream();
        salida =  cliente.getOutputStream();
        datosentrada = new DataInputStream(entrada);
        datossalida=new DataOutputStream(salida);
          
        long size = datosentrada.readLong();
        System.out.println("Size : "+ size);
        String nombre =  datosentrada.readUTF();
        System.out.println("Name : "+ nombre);
        int bytes =0;
        byte buffer[] = new byte[(int)size];
       escribirarchivo = new FileOutputStream("recibido_"+nombre);// escribir el archivo de lado del servidor

        while(size > 0 && (bytes = datosentrada.read(buffer,0,buffer.length))!=-1){
           escribirarchivo.write(buffer,0, bytes);
           size -= bytes; 
        
        }
        System.out.println("Servidor dice: Recibido");
        escribirarchivo.close();
        cliente.close();
        }catch(IOException e){
          e.printStackTrace();
        }
}
    public static void main(String args[]){
    ServidorArchivo sa = new ServidorArchivo();
    }
}
