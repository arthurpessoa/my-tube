/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytube.ws;

import com.mytube.ws.wsdl.FileNotFoundException_Exception;
import com.mytube.ws.wsdl.IOException_Exception;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.soap.SOAPBinding;

import com.mytube.ws.wsdl.MyTubeWS;
import com.mytube.ws.wsdl.MyTubeWebService;
import com.mytube.ws.wsdl.NotBoundException_Exception;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;
import org.kobjects.base64.Base64;

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

    public void download(String name) throws IOException, FileNotFoundException_Exception, IOException_Exception, NotBoundException_Exception {
        MyTubeWebService service = new MyTubeWebService(url, qname);
        MyTubeWS videoServer = service.getMyTubeWSPort();

        BindingProvider bindingProvider = (BindingProvider) videoServer;
        SOAPBinding sopadBinding = (SOAPBinding) bindingProvider.getBinding();
        sopadBinding.setMTOMEnabled(true);
        try (OutputStream a = (new FileOutputStream("e://" + name))) {
            BufferedOutputStream os = new BufferedOutputStream(a);
            videoServer.initStream(name);
            os.write(Base64.decode(videoServer.download()));
            os.flush();
            os.close();
            //videoServer.stopStream();
        }
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
