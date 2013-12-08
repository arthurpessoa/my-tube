/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mytubermiserver.rmi;
import java.rmi.Remote;
import com.healthmarketscience.rmiio.RemoteInputStream;
import java.io.IOException;
/**
 *
 * @author Arthur
 */

public interface Server extends Remote {
    
    public void sendFile(String fileName,RemoteInputStream inFile) throws IOException;
    public RemoteInputStream getFile(String fileName) throws IOException;
}