/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acr;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Dell
 */
public class ClienteTCP1 {

    Socket cliente;
    OutputStream salida;
    InputStream entrada;

    public ClienteTCP1() {
        try {
            cliente = new Socket("127.0.0.1", 1025);//ipp y puerto del servidor
            salida = cliente.getOutputStream();
            entrada = cliente.getInputStream();
            int dato= entrada.read();// leer lo qu emanda le servidor
            System.out.println("El servidor mando: "+dato);
           //mndarle mesnaje al servicodr
           String saludo  ="Hola servidor";
           byte b[] = saludo.getBytes();
             salida.write(b);
            byte mensaje[] = new byte[15];
            int cb = entrada.read(mensaje);
            System.out.println("Enteros: "+cb);   
            String msg = new String(mensaje);
            System.out.println(msg);
           cliente.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String args[]){
       ClienteTCP1 c1 = new ClienteTCP1();
    }
}
