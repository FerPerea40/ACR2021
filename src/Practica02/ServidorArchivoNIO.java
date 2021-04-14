/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica02;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 *José Fernando Perea Macías
 * @author Dell
 * Socket -> Envio de Archivos
 * El programa consiste en una Servidor y un Cliente, donde por medio de sockets
 * podemos enviar archivos de diferente extensión, desde el cliente hacia el 
 * servidor finalmente el servidior se encarga de guardarlo con un identificador
 * mpas el nombre del archivo y su respectiva extensión.
 * 04/03/2021
 */
public class ServidorArchivoNIO {

    ServerSocketChannel servidor;
    SocketChannel cliente;
    ByteBuffer buffer;
    FileChannel file;

    public ServidorArchivoNIO() {
        try {
            servidor = ServerSocketChannel.open();
            InetSocketAddress ip = new InetSocketAddress(5512);// define la ip y puerto del servidor
            servidor.socket().bind(ip);
            servidor.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void escribirArchivo() {
        String ruta = "";
        try {
            while (true) {
                cliente = servidor.accept();
                if (cliente != null) {
                    buffer = ByteBuffer.allocate(1024);
                    System.out.println("Cliente: " + cliente.getRemoteAddress());
                    cliente.read(buffer);
                    buffer.flip();

                    while (buffer.hasRemaining()) {
                        ruta = ruta + (char) buffer.get();
                    }
                    System.out.println("Rutita: " + ruta);
                    buffer.clear();

                    file = new RandomAccessFile("ArchivosNIO_Recibidos/" + ruta, "rw").getChannel();
                    buffer = ByteBuffer.allocate(1024);
                    
                    while (cliente.read(buffer) > 0) {
                        buffer.flip();
                        file.write(buffer);
                        buffer.clear();
                    }
                    file.close();
                    System.out.println("Archivo recibido...");
                    cliente.close();
                }
                ruta = "";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {

        ServidorArchivoNIO ser = new ServidorArchivoNIO();
        ser.escribirArchivo();
    }
}
