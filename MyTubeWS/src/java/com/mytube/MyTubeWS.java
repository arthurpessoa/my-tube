/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mytube;

import mytubermiserver.RMIClient;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.activation.DataHandler;
import com.sun.xml.ws.developer.StreamingDataHandler;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.MTOM; //Teste pra passar arquivos via stream (otimização de RAM pra arquivos grandes)
import javax.mail.util.ByteArrayDataSource;
import org.apache.commons.io.IOUtils;
import org.kobjects.base64.Base64;

//Homem da Corba
import org.omg.CORBA.*;
import org.omg.CosNaming.*;


//RMI 
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;



/**
 *
 * @author Arthur
 */

@MTOM
@WebService(serviceName = "MyTubeWebService")
@Stateless()
public class MyTubeWS {

    int read = 0;
    InputStream f;
    File file;
    public int getRead(){
        return read;
    }
    
    public void initStream(String fileName) throws FileNotFoundException, RemoteException, RemoteException, NotBoundException, IOException{
        RMIClient rmiClient = new RMIClient("192.168.0.145","MyTubeRMI");        
        f = rmiClient.download(fileName);        
    }    
    
    public void stopStream() throws IOException{        
        f.close();
        file.delete();
    }
    
    public String download() throws FileNotFoundException, IOException { 
        
        return Base64.encode(IOUtils.toByteArray(f));
    }
    

    public void videoUpload(String videoName, String desc, @XmlMimeType("application/octet-stream") DataHandler data) throws RemoteException, NotBoundException {
        
        
        String videoID="bbb"; //arruma isso jao SEM PONTO E VIRGULA!!! 
        //---------RMI----Não toque porra        
        File file=null;
        //Video handler
        try (StreamingDataHandler dh = (StreamingDataHandler) data) {
            file = new File("e://WS/" + videoName);
            dh.moveTo(file);
            dh.close();
            try (InputStream is = new FileInputStream(file)) {
               RMIClient rmiClient = new RMIClient("192.168.0.145","MyTubeRMI");                
               rmiClient.upload(videoID, is);
            }            
        } catch (IOException e) {
            throw new WebServiceException(e);
        }
        file.delete();
    }
}