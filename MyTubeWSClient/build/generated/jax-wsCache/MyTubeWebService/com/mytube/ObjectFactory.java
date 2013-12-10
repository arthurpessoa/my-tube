
package com.mytube;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.mytube package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _VideoUploadResponse_QNAME = new QName("http://mytube.com/", "videoUploadResponse");
    private final static QName _VideoUpload_QNAME = new QName("http://mytube.com/", "videoUpload");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.mytube
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link VideoUploadResponse }
     * 
     */
    public VideoUploadResponse createVideoUploadResponse() {
        return new VideoUploadResponse();
    }

    /**
     * Create an instance of {@link VideoUpload }
     * 
     */
    public VideoUpload createVideoUpload() {
        return new VideoUpload();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VideoUploadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mytube.com/", name = "videoUploadResponse")
    public JAXBElement<VideoUploadResponse> createVideoUploadResponse(VideoUploadResponse value) {
        return new JAXBElement<VideoUploadResponse>(_VideoUploadResponse_QNAME, VideoUploadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VideoUpload }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mytube.com/", name = "videoUpload")
    public JAXBElement<VideoUpload> createVideoUpload(VideoUpload value) {
        return new JAXBElement<VideoUpload>(_VideoUpload_QNAME, VideoUpload.class, null, value);
    }

}
