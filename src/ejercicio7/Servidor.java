/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio7;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Dell
 */
public class Servidor {
    ServerSocket servidor;
    Socket cliente;
    ObjectOutputStream objsalida;
    ObjectInputStream objentrada;
    
    public Servidor(){
    try{
        servidor = new ServerSocket(1028);
        
        cliente = servidor.accept();
        objentrada = new ObjectInputStream(cliente.getInputStream());
        
        Empleado emp = (Empleado)objentrada.readObject();
        System.out.println(emp.toString());
    
    }catch(IOException ex){
        ex.printStackTrace();
    }catch(ClassNotFoundException e){
       e.printStackTrace();
    
    }
    }
    public static void main( String args[]){
      Servidor serv =  new Servidor();
    
    }
    
    
    
}
