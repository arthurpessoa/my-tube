/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytubermiserver;

import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import com.healthmarketscience.rmiio.GZIPRemoteInputStream;
import com.healthmarketscience.rmiio.RemoteInputStreamServer;
import com.healthmarketscience.rmiio.RemoteInputStreamClient;
import com.healthmarketscience.rmiio.RemoteOutputStreamClient;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Arthur
 */


public class MyTubeRMIClient {

    /**
     * @param args the command line arguments
     */
    /*
    public static void main(String[] args) {
        
        System.setProperty("com.healthmarketscience.rmiio.exporter.port", "6667");
        
        
        try {
            Registry registry = LocateRegistry.getRegistry("192.168.0.145"); //ENDERECO
            Server server = (Server) registry.lookup("MyTubeRMI");

            System.out.println(server.testConnection());

            //ENVIAR ARQUIVO PRO SERVER
            
            InputStream istream = new FileInputStream("e://music.mp3");
            // call server (note export() call to get actual remote interface)
            OutputStream ostream = RemoteOutputStreamClient.wrap(server.uploadOutputStream("arthur"));
                
            ostream.write(IOUtils.toByteArray(istream));
            ostream.flush();            
            ostream.close();
          
            server.saveInDatabase("arthur");
            
             //RECEBER ARQUIVO DO SERVER
             InputStream istreamSaida = RemoteInputStreamClient.wrap(server.getFile("arthur"));
            
             int read;
             try (FileOutputStream out = new FileOutputStream("e:/music2.mp3")) {
             byte[] bytes = new byte[1024];
             while ((read = istreamSaida.read(bytes)) != -1) {
             out.write(bytes, 0, read);
             }
             out.close();
             } 
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Exception: " + e);
        } catch (IOException ex) {
            System.out.println("Exception: " + ex);
        }

    }
*/    
}
