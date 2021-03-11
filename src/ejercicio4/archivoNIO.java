/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio4;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 * @author Dell
 */
public class archivoNIO {
    
    FileChannel canal;
    ByteBuffer buffer;
    
    public archivoNIO(){
       try{
       canal = new RandomAccessFile("build.xml","rw").getChannel();
       buffer = ByteBuffer.allocate(2500);
       }catch(FileNotFoundException e){
        e.printStackTrace();
       }
    }
    
    public void escribir(String msg){
    try{
    buffer.clear();
    buffer.put(msg.getBytes());
    buffer.flip();
    
    canal.write(buffer);
    canal.close();
    }catch(IOException e){
      e.printStackTrace();
    }}
    
    public void leer(){
        try{
        int leido = canal.read(buffer);
        while(leido!=-1){
        System.out.println("Bytes leídos:"+leido);
        buffer.flip();
        while(buffer.hasRemaining()){
        System.out.print((char)buffer.get());
         }
      buffer.clear();
        leido=canal.read(buffer);
        }
          System.out.println("\n");
        canal.close();
        }catch(IOException e){
        e.printStackTrace();
        }
       }
//    public archivoNIO(){
//       try{
//    RandomAccessFile aFile = new RandomAccessFile("prueba1.txt", "rw");
//        FileChannel inChannel = aFile.getChannel();
//
//        ByteBuffer buf = ByteBuffer.allocate(48);
//        int bytesRead = inChannel.read(buf);
//        while (bytesRead != -1) {
//            System.out.println("Read " + bytesRead);
//            buf.flip();
//            while (buf.hasRemaining()) {
//                System.out.println((char) buf.get());
//            }
//            buf.clear();
//            bytesRead = inChannel.read(buf);
//        }
//        aFile.close();
//    }
//    catch(IOException e){
//    e.printStackTrace();
//}
//}
    
    public static void main(String args[]){
    archivoNIO nio =  new archivoNIO();
    System.out.println("Escribe el mensjaje");
    //nio.escribir("Hola hola");
   nio.leer();
    }
}