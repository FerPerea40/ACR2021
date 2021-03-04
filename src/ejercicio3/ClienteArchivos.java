/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Dell
 */
public class ClienteArchivos {
    Socket cliente;
    InputStream entrada;
    OutputStream salida;
    DataInputStream datosentrada;
    DataOutputStream datossalida;
    //Manejo del Archivo a enviar
    File file ;
    FileInputStream lectura;
    
    public ClienteArchivos(String ip,int port){
        try{
        cliente = new Socket(ip,port);
        entrada = cliente.getInputStream();
        salida =cliente.getOutputStream();
        datosentrada = new DataInputStream(entrada);
        datossalida = new DataOutputStream(salida);
        //envo de datos
        //Paso 1: leer archivo
        //Paso 2: enviar el contenido del archivo
        file = new File("prueba1.txt");
        lectura = new FileInputStream(file);
        //enviar el tamaño del archivo al servidor
        datossalida.writeLong(file.length());
        byte[] buffer = new byte[1024]; // 65kb
        int bytes = 0;
        while((bytes = lectura.read(buffer))!=-1){
          datossalida.write(buffer,0,bytes);
          datossalida.flush();
        
        }
        lectura.close();
        cliente.close();
        
        }catch(UnknownHostException e){
            e.printStackTrace();
        }catch(IOException e){
          e.printStackTrace();
        }
       
    }
    public static void main(String args[]){
        
    ClienteArchivos ca = new ClienteArchivos("127.0.0.1",1027);
    }
    
}
