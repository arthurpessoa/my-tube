/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mytubermiserver;
import java.rmi.Remote;
import com.healthmarketscience.rmiio.RemoteInputStream;
import com.healthmarketscience.rmiio.RemoteOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
/**
 *
 * @author Arthur
 */

public interface Server extends Remote {
    
    public RemoteOutputStream uploadOutputStream(String fileName) throws IOException;
    public RemoteInputStream getFile(String fileName) throws IOException;
    public void saveInDatabase(String fileName) throws IOException;
    public String testConnection() throws RemoteException;
}