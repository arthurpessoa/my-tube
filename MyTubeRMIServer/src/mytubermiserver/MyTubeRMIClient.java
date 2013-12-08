/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytubermiserver;

import java.rmi.NotBoundException;
import mytubermiserver.rmi.Server;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import com.healthmarketscience.rmiio.GZIPRemoteInputStream;
import com.healthmarketscience.rmiio.RemoteInputStreamServer;
import com.healthmarketscience.rmiio.RemoteInputStreamClient;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 *
 * @author Arthur
 */
public class MyTubeRMIClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RemoteInputStreamServer istream = null;
        try {
            Registry registry = LocateRegistry.getRegistry("localhost"); //ENDERECO
            Server server = (Server) registry.lookup("MyTubeRMI");
            
            //ENVIAR ARQUIVO PRO SERVER
            istream = new GZIPRemoteInputStream(new BufferedInputStream(new FileInputStream("e:/ibajem.jpg")));
            server.sendFile("teste", istream.export());
            
            //RECEBER ARQUIVO DO SERVER
            
            
            InputStream istreamSaida = RemoteInputStreamClient.wrap(server.getFile("teste"));
            
            int read;
            try (FileOutputStream out = new FileOutputStream("e:/ibajem2.jpg")) {
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
        } finally {
            if (istream != null) {
                istream.close();
            }
        }

    }
}
