/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio7;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;

/**
 *
 * @author Dell
 */
public class ClienteNIO {
    
    SocketChannel cliente;
    ObjectInputStream objentrada;
    ObjectOutputStream objsalida;
    
    public ClienteNIO(){
        try{
            cliente =  SocketChannel.open();
        InetSocketAddress ip =  new InetSocketAddress("127.0.0.1", 1029);
        cliente.connect(ip);
        objentrada =  new ObjectInputStream(Channels.newInputStream(cliente));
        
        Empleado temp = (Empleado)objentrada.readObject();
        System.out.println(temp.toString());
        objentrada.close();
        cliente.close();
        }catch(IOException e){
         e.printStackTrace();
        }catch(ClassNotFoundException e){
        e.printStackTrace();
        }
    
    }
    public static void main(String args[]){
     ClienteNIO cn = new ClienteNIO();
    
    
    }
}
