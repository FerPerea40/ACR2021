/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Dell
 */
public class servidorTCP2 {

  ServerSocket servidor;
    Socket cliente;
    InputStream entrada;
    OutputStream salida;
    DataInputStream datosentrada;
    DataOutputStream datossalida;

    public servidorTCP2() {
        try {
            servidor = new ServerSocket(1025);
            while (true) {
                cliente = servidor.accept();
                entrada = cliente.getInputStream();
                salida = cliente.getOutputStream();

                datosentrada = new DataInputStream(entrada);
                datossalida = new DataOutputStream(salida);
                // RECIBE MENSAJE Y LO MUESTRA
                while (true) {
                    String msg = datosentrada.readUTF();
                    System.out.println("El cliente dice> " + msg);
                    if(msg.equalsIgnoreCase("adios"))
                        break;
                }
                // ENVIAR ENTERO LARGO
                datossalida.writeLong(12345678909L);

                cliente.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        servidorTCP2 serv2 = new servidorTCP2();
    }
    }

