/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytube.control;

import com.mytube.MyTubeWebService_Service;
import com.mytube.MyTubeWebService;

import java.io.File;
import java.io.FileInputStream;

import java.util.Iterator;
import java.util.List;
import java.net.URL;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.soap.SOAPBinding;
import org.apache.commons.fileupload.FileUploadException;;


/**
 *
 * @author Arthur
 */
public class Upload {

    public boolean anexos(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //-----------------Upload pra um arquivo temporário -------------//
        File temporaryUploadFile = null;

        if (ServletFileUpload.isMultipartContent(request)) {

            int cont = 0;

            ServletFileUpload servletFileUpload = new ServletFileUpload(
                    new DiskFileItemFactory());

            List fileItemsList;
            fileItemsList = servletFileUpload.parseRequest(request);

            String optionalFileName = "";
            String videoDescription = "";
            
            FileItem fileItem = null;

            Iterator it = fileItemsList.iterator();
            do {
                cont++;
                FileItem fileItemTemp = (FileItem) it.next();

                if (fileItemTemp.isFormField()) {
                    if (fileItemTemp.getFieldName().equals("filename")) {
                        optionalFileName = fileItemTemp.getString();
                    }
                    if (fileItemTemp.getFieldName().equals("description")) {
                        videoDescription=(fileItemTemp.getString());
                    }
                    
                } else {
                    fileItem = fileItemTemp;
                }

                if (cont != (fileItemsList.size())) {
                    if (fileItem != null) {

                        String fileName = fileItem.getName();

                        if (fileItem.getSize() > 0) {

                            if (optionalFileName.trim().equals("")) {
                                fileName = FilenameUtils.getName(fileName);
                            } else {
                                fileName = optionalFileName;
                            }
                            String dirName = "E://"; //caminho para o projeto
                            temporaryUploadFile = new File(dirName + fileName);
                            fileItem.write(temporaryUploadFile);

                        }
                    }
                }
            } while (it.hasNext());
            //----------------------Fim Upload ----------------------

            //Configurar conexão WS
            URL url = new URL("http://localhost:8080/MyTubeWebService/MyTubeWebService?wsdl");
            QName qname = new QName("http://mytube.com/", "MyTubeWebService");

            //Ativar MTOM
            MyTubeWebService_Service service = new MyTubeWebService_Service(url, qname);
            MyTubeWebService videoServer = service.getMyTubeWebServicePort();

            BindingProvider bindingProvider = (BindingProvider) videoServer;
            SOAPBinding sopadBinding = (SOAPBinding) bindingProvider.getBinding();
            sopadBinding.setMTOMEnabled(true);
            
            
            if (temporaryUploadFile != null) {
                try (FileInputStream fis = new FileInputStream(temporaryUploadFile.getAbsolutePath())) {
                    ByteArrayDataSource videoData = new ByteArrayDataSource(fis,"application/octet-stream");
                    
                    
                    DataSource rawData = videoData;
                    DataHandler videoHandler = new DataHandler(rawData);
                    
                    videoServer.videoUpload(temporaryUploadFile.getName(), videoDescription, videoHandler);
                    
                    
                }
                return true; 
            }else{
                return false;
            }         
        }
        return false;
        
    }
}
