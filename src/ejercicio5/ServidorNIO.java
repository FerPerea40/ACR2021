/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio5;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 *
 * @author Dell
 */
public class ServidorNIO {
    ServerSocketChannel servidor;
    SocketChannel cliente;
    ByteBuffer buffer;
    String saludo;
    public ServidorNIO(int port, String saludo){
        try{
            this.saludo=saludo;
        servidor =ServerSocketChannel.open();
        InetSocketAddress ip = new InetSocketAddress(port);
        servidor.socket().bind(ip);
        servidor.configureBlocking(false);
        
        }catch(IOException e){
        e.printStackTrace();
        }
    }
    public void escucha(){
        try{
    buffer=ByteBuffer.wrap(saludo.getBytes());
    while(true){
    System.out.println("El servidor esta activo");
    cliente=servidor.accept();
    if(cliente == null){
    Thread.sleep(2000);
    }else{
        System.out.println("Se conectó: "+cliente.socket().getLocalPort());
         buffer.rewind();
         cliente.write(buffer);
         cliente.close();
    }
    }}catch(IOException e){
    e.printStackTrace();
    }catch(InterruptedException e){
    e.printStackTrace();
    }
    
    }
    public static void main(String args[]){
    
    ServidorNIO sn = new ServidorNIO (2018,"Hola cliente");
    sn.escucha();
    }
}
