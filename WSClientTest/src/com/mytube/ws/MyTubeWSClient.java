/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytube.ws;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.soap.SOAPBinding;

import com.mytube.ws.wsdl.MyTubeWS;
import com.mytube.ws.wsdl.MyTubeWebService;
import java.io.IOException;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;

/**
 *
 * @author Arthur
 */
public class MyTubeWSClient {

    private final URL url;
    private final QName qname;

    public MyTubeWSClient(String URL) throws MalformedURLException {
        url = new URL(URL);
        qname = new QName("http://mytube.com/", "MyTubeWebService");
    }

    public void upload(String name, String description, InputStream istream) throws IOException {
        MyTubeWebService service = new MyTubeWebService(url, qname);
        MyTubeWS videoServer = service.getMyTubeWSPort();

        BindingProvider bindingProvider = (BindingProvider) videoServer;
        SOAPBinding sopadBinding = (SOAPBinding) bindingProvider.getBinding();
        sopadBinding.setMTOMEnabled(true);

        ByteArrayDataSource videoData = new ByteArrayDataSource(istream, "application/octet-stream");

        DataSource rawData = videoData;
        DataHandler videoHandler = new DataHandler(rawData);

        videoServer.videoUpload(name, description, videoHandler);

    }
}
