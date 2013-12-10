/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mytube.rmi;
import com.healthmarketscience.rmiio.RemoteInputStreamClient;
import com.healthmarketscience.rmiio.RemoteOutputStreamClient;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Arthur
 */
public class RMIClient {
  
    private final Server server;
    
    public RMIClient(String address,String registryName ) throws RemoteException, NotBoundException{
        Registry registry = LocateRegistry.getRegistry(address);
        server = (Server) registry.lookup(registryName);
    }
    
    public InputStream download(String fileName) throws IOException{
        return RemoteInputStreamClient.wrap(server.getFile(fileName));
    }
    
    public void upload(String fileName, InputStream videoStream) throws RemoteException, NotBoundException, IOException{
        try (OutputStream ostream = RemoteOutputStreamClient.wrap(server.uploadOutputStream(fileName))) {
            ostream.write(IOUtils.toByteArray(videoStream));
            ostream.flush();
            ostream.close();
            server.saveInDatabase(fileName);
        }          
    }
}
