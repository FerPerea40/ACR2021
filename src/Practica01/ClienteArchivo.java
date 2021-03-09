/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica01;

import ejercicio3.ClienteArchivos;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JFileChooser;

/**
 *
 * @author Dell
 */
public class ClienteArchivo {
     Socket cliente;
    InputStream entrada;
    OutputStream salida;
    DataInputStream datosentrada;
    DataOutputStream datossalida;
    File file ;
    FileInputStream lectura;
    
    public ClienteArchivo(String ip,int port){
        try{
        cliente = new Socket(ip,port);
        entrada = cliente.getInputStream();
        salida =cliente.getOutputStream();
        datosentrada = new DataInputStream(entrada);
        datossalida = new DataOutputStream(salida);
        JFileChooser fileo = new JFileChooser();
        fileo.showOpenDialog(fileo);
        file = fileo.getSelectedFile();
  
        System.out.println("Archivo Seleccionado: "+file.getName());
        lectura = new FileInputStream(file);
        datossalida.writeLong(file.length());
        datossalida.writeUTF(file.getName());
        byte[] buffer = new byte[(int)file.length()]; // 65kb
        int bytes = 0;
        while((bytes = lectura.read(buffer))!=-1){
          datossalida.write(buffer,0,bytes);
          datossalida.flush();
         }
        System.out.println("Cliente dice: enviados");
        lectura.close();
        cliente.close();
        
        }catch(UnknownHostException e){
            e.printStackTrace();
        }catch(IOException e){
          e.printStackTrace();
        }
       
    }
    public static void main(String args[]){
        
    ClienteArchivo ca = new ClienteArchivo("127.0.0.1",1027);
    }
  }
