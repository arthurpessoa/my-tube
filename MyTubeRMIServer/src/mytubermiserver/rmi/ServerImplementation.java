/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytubermiserver.rmi;

import mytubermiserver.mongo.GridFileSystem;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import com.healthmarketscience.rmiio.RemoteInputStream;
import com.healthmarketscience.rmiio.RemoteInputStreamServer;
import com.healthmarketscience.rmiio.RemoteInputStreamClient;
import com.healthmarketscience.rmiio.GZIPRemoteInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Arthur
 */
public class ServerImplementation extends UnicastRemoteObject implements Server {

    private final String hostAddress = "192.168.242.128"; 
    
    public ServerImplementation() throws RemoteException {
    }

    @Override
    public void sendFile(String fileName, RemoteInputStream inFile) throws IOException {
        InputStream istream = RemoteInputStreamClient.wrap(inFile);
        GridFileSystem gfs = new GridFileSystem(hostAddress, 27017);
        gfs.sendVideo(fileName, istream);
    }

    @Override
    public RemoteInputStream getFile(String fileName) throws IOException {
        RemoteInputStreamServer istream = null;
        GridFileSystem gfs = new GridFileSystem(hostAddress, 27017);
        
        try {
            istream = new GZIPRemoteInputStream(new BufferedInputStream(gfs.receiveVideo(fileName)));
            RemoteInputStream result;
            
            result = istream.export();
            istream = null;
            return result;
        } finally {
            if (istream != null) {
                istream.close();
            }
        }
    }

}
