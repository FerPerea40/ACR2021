/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio7;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 *
 * @author Dell
 */
public class ServidorNIO {
    ServerSocketChannel servidor;
    SocketChannel cliente;
    ObjectOutputStream objsalida;
    ObjectInputStream objentrada;
    
    public ServidorNIO(){
        try{
    servidor = ServerSocketChannel.open();
    InetSocketAddress ip = new InetSocketAddress(1029);
    servidor.socket().bind(ip);
    servidor.configureBlocking(false);
    while(true){
    System.out.println("Servidor Activo");
    cliente = servidor.accept();
    if (cliente == null){
    
    Thread.sleep(2000);
    }else{
        objsalida = new ObjectOutputStream(Channels.newOutputStream(cliente));
        Empleado emp =  new Empleado();
        
        emp.setNombre("Villano");
        emp.setEmail("villano@villanolandia.com");
       
        objsalida.writeObject(emp);
        objsalida.close();
    }
    
    }
        }catch(IOException e){
        e.printStackTrace();
        }catch(InterruptedException e){
        e.printStackTrace();
        }
    }
    
    public static void main(String args[]){
        ServidorNIO sn = new ServidorNIO();    
    
    }
}
