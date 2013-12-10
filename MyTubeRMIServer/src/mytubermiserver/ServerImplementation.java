/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytubermiserver;

import mytubermiserver.Server;
import mytubermiserver.mongo.GridFileSystem;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import com.healthmarketscience.rmiio.RemoteInputStream;
import com.healthmarketscience.rmiio.RemoteInputStreamServer;
import com.healthmarketscience.rmiio.RemoteInputStreamClient;
import com.healthmarketscience.rmiio.GZIPRemoteInputStream;
import com.healthmarketscience.rmiio.GZIPRemoteOutputStream;
import com.healthmarketscience.rmiio.RemoteOutputStream;
import com.healthmarketscience.rmiio.RemoteOutputStreamServer;
import com.healthmarketscience.rmiio.SimpleRemoteOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;

/**
 *
 * @author Arthur
 */
public class ServerImplementation extends UnicastRemoteObject implements Server {

    private final String hostAddress = "192.168.0.133";

    public ServerImplementation() throws RemoteException {
    }

    @Override
    public void saveInDatabase(String fileName) throws IOException{
        
        File f = new File("./temp/"+fileName);
        try (InputStream istream = new FileInputStream(f)) {
            GridFileSystem gfs = new GridFileSystem(hostAddress, 27017);
            gfs.sendVideo(fileName, istream);
            istream.close();
        }
        f.delete();
    }
    
    @Override
     public RemoteOutputStream uploadOutputStream(String fileName) throws IOException {
      RemoteOutputStreamServer outstream;
      outstream = new SimpleRemoteOutputStream(new BufferedOutputStream(new FileOutputStream("./temp/"+fileName)));
      return outstream.export();   
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

    @Override
    public String testConnection() throws RemoteException {
        return "Online MADAFAKA!";
    }

}
