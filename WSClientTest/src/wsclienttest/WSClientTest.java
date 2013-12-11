/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wsclienttest;

/**
 *
 * @author Arthur
 */


import com.mytube.ws.MyTubeWSClient;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WSClientTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here

            MyTubeWSClient ws = new MyTubeWSClient("http://localhost:8080/MyTubeWebService/MyTubeWS?wsdl");  
            ws.upload("ijabem.jpg", "lala", new FileInputStream("e://ibajem.jpg"));       
        
        } catch (MalformedURLException ex) {
            Logger.getLogger(WSClientTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WSClientTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
