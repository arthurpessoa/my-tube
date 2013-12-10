
package com.mytube;

import javax.activation.DataHandler;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebService(name = "MyTubeWebService", targetNamespace = "http://mytube.com/")
@XmlSeeAlso({
    com.mytube.ObjectFactory.class,
    org.w3._2005._05.xmlmime.ObjectFactory.class
})
public interface MyTubeWebService {


    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "videoUpload", targetNamespace = "http://mytube.com/", className = "com.mytube.VideoUpload")
    @ResponseWrapper(localName = "videoUploadResponse", targetNamespace = "http://mytube.com/", className = "com.mytube.VideoUploadResponse")
    @Action(input = "http://mytube.com/MyTubeWebService/videoUploadRequest", output = "http://mytube.com/MyTubeWebService/videoUploadResponse")
    public void videoUpload(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        DataHandler arg2);

}