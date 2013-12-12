/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package wsclienttest;

import com.mytube.ws.MyTubeWSClient;
import com.mytube.ws.wsdl.FileNotFoundException_Exception;
import com.mytube.ws.wsdl.IOException_Exception;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class WSClientTest {
    public static void main(String[] args) throws FileNotFoundException_Exception, IOException_Exception {
        try {
            // TODO code application logic here

            MyTubeWSClient ws = new MyTubeWSClient("http://localhost:8080/MyTubeWebService/MyTubeWS?wsdl");
            //ws.upload("ijabem.jpg", "lala", new FileInputStream("e://ibajem.jpg"));
            ws.download("bbb");
        
        } catch (MalformedURLException ex) {
            Logger.getLogger(WSClientTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WSClientTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
