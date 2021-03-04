/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Dell
 */
public class ServidorArchivos {
     ServerSocket servidor;
    Socket cliente;
    InputStream entrada;
    OutputStream salida;
    DataInputStream datosentrada;
    DataOutputStream datossalida;

    FileOutputStream escribirarchivo;
    
    public ServidorArchivos(){
        try{
        servidor = new ServerSocket(1027);
        System.out.println("El servidor está a la escucha en el puerto 1027");
        //while(true){}
        cliente= servidor.accept();
        entrada =  cliente.getInputStream();
        salida =  cliente.getOutputStream();
        datosentrada = new DataInputStream(entrada);
        datossalida=new DataOutputStream(salida);
        //
        escribirarchivo = new FileOutputStream("archivorecivido.txt");// escribir el archivo de lado del servidor
        //sabe rtamaño del archivo a recibir
        int bytes =0;
        byte buffer[] = new byte[1024];
        long size = datosentrada.readLong();
        System.out.println("Size : "+ size);
        while(size > 0 && (bytes = datosentrada.read(buffer,0,buffer.length))!=-1){
           escribirarchivo.write(buffer,0, bytes);
           size -= bytes; 
        
        }
        escribirarchivo.close();
        cliente.close();
        }catch(IOException e){
          e.printStackTrace();
        }
}
    public static void main(String args[]){
    ServidorArchivos sa = new ServidorArchivos();
    }
}
