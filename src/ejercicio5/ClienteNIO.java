/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio5;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 *
 * @author Dell
 */
public class ClienteNIO {
    SocketChannel cliente;
    SocketAddress ip;
    ByteBuffer buffer;
    
    public ClienteNIO(){
        try{
            cliente = SocketChannel.open();
            ip = new InetSocketAddress("127.0.0.1",2018);
            cliente.connect(ip);
               
        }catch(IOException e){
        e.printStackTrace();
        }
    }
    
    public void lectura(){
        try{
    buffer=ByteBuffer.allocate(1024);
    buffer.flip();
    
    while(cliente.read(buffer) > -1){
        buffer.flip();
        while(buffer.hasRemaining()){
         System.out.print((char)buffer.get());
        
        }
                 System.out.print("\n");
                buffer.clear();
        }

    
    }catch(IOException e){
    e.printStackTrace();
    }
    
    }
    
       public static void main(String args[]){
    
    ClienteNIO sn = new ClienteNIO ();
    sn.lectura();
    }
}
