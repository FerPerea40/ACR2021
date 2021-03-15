/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica02;

import java.io.FileInputStream;
import java.nio.channels.SocketChannel;
import java.nio.ByteBuffer;
import java.io.IOException;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.io.RandomAccessFile;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;

/**
 * José Fernando Perea Macías
 *
 * @author Dell Socket -> Envio de Archivos El programa consiste en una Servidor
 * y un Cliente, donde por medio de sockets podemos enviar archivos de diferente
 * extensión, desde el cliente hacia el servidor finalmente el servidior se
 * encarga de guardarlo con un identificador mpas el nombre del archivo y su
 * respectiva extensión. 04/03/2021
 */
public class ClienteArchivoNIO {

    SocketChannel cliente;
    ByteBuffer buffer;
    ByteBuffer buffer2;
    FileChannel file;

    public ClienteArchivoNIO() {
        try {
            cliente = SocketChannel.open();
            SocketAddress ip_ser = new InetSocketAddress("127.0.0.1", 5512);
            cliente.connect(ip_ser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mandarArchivo(String ruta_archivo, String nombre) {
        try {
            buffer = ByteBuffer.wrap(nombre.getBytes());
            buffer.rewind();
            cliente.write(buffer);
            buffer.clear();

            file = new RandomAccessFile(ruta_archivo, "r").getChannel();
            buffer = ByteBuffer.allocate(1024);
            while (file.read(buffer) > 0) {
                buffer.flip();
                cliente.write(buffer);
                buffer.clear();
            }
            file.close();
            System.out.println("Archivo enviado...");
            cliente.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        ClienteArchivoNIO c1 = new ClienteArchivoNIO();
        JFileChooser fileo = new JFileChooser();
        fileo.showOpenDialog(fileo);

        System.out.println("ArchivoSel: " + fileo.getSelectedFile().getName());
        c1.mandarArchivo(fileo.getSelectedFile().getPath(), fileo.getSelectedFile().getName());
    }
}
