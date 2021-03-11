/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio6;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 *
 * @author Dell
 */
public class ClienteArchivoNIO {
    SocketChannel cliente;
    ByteBuffer buffer;
    FileChannel file;
    
    public ClienteArchivoNIO(){
        try{
       cliente = SocketChannel.open();
       SocketAddress ip_ser = new InetSocketAddress("127.0.0.1",5021);
               
               cliente.connect(ip_ser);
               
        }catch(IOException e){
        e.printStackTrace();
        
        }
    
    }
    
    public void mandarArchivo(String ruta_archivo){
        try{
       file = new RandomAccessFile(ruta_archivo,"r").getChannel();
       buffer = ByteBuffer.allocate(1024);
       
       while(file.read(buffer)>0){
          buffer.flip();
          cliente.write(buffer);
          buffer.clear();
       }
        }catch(FileNotFoundException e){
          
            e.printStackTrace();
        
        }catch(IOException e){
        e.printStackTrace();
        
        }
    
    }
    
}
