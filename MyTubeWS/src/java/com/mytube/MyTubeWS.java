	
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mytube;

import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.activation.DataHandler;
import com.sun.xml.ws.developer.StreamingDataHandler;
import java.io.File;
import java.io.IOException;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.MTOM; //Teste pra passar arquivos via stream (otimização de RAM pra arquivos grandes)
/**
 *
 * @author Arthur
 */

@MTOM
@WebService(serviceName = "MyTubeWebService")
@Stateless()
public class MyTubeWS {
    
    
    
    public void videoUpload(String videoName,String desc, @XmlMimeType("application/octet-stream") DataHandler data) {
        File file;        
        //Video handler
        try (StreamingDataHandler dh = (StreamingDataHandler) data) {                
                file = new File("e://WS/"+videoName);
                dh.moveTo(file);
                dh.close();
                        
         }
         catch (IOException e) {
            throw new WebServiceException(e);
        }
    }
}