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
import http.Arquivo;
import http.Manip;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
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
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import projsd.Client;
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
    
    public String download(String chave) throws FileNotFoundException, IOException { 
        Arquivo arquivo = new Arquivo();
        Client cliente = new Client();
        if(cliente.keyCheck(chave)){
            MyTubeWS upDown = new MyTubeWS();
            String nome = upDown.receberNome(chave);
            String descr = upDown.receberDescr(chave);
            return Base64.encode(IOUtils.toByteArray(f));
        }
        System.out.println("Nao achei a chave");
        return null;
    }
    

    public String videoUpload(String videoName, String desc, @XmlMimeType("application/octet-stream") DataHandler data) throws RemoteException, NotBoundException, IOException {
        
        Arquivo arquivo = new Arquivo();
        Client cliente = new Client();
        String chave = cliente.newKey();
        System.out.println(chave);
        if(cliente.keyCheck(chave)){
            arquivo.setChave(chave);
            arquivo.setDado(desc);
            arquivo.setNome(videoName);
            Manip upDown = new Manip();
            upDown.enviarArquivo(arquivo);
            
            String videoID=chave; //arruma isso jao SEM PONTO E VIRGULA!!! 
            //---------RMI----Não toque porra        
            File file=null;
            //Video handler
            try (StreamingDataHandler dh = (StreamingDataHandler) data) {
                file = new File("" + videoName);
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
        return chave;
    }
    
     public String receberDescr(String key) throws UnsupportedEncodingException, IOException{
        Arquivo arquivo;
        String descr = null;
        try{
            URL addGetRequest = new URL("http://mytube-bd.appspot.com/serve?method=view&chave="+key);
            URLConnection conn = addGetRequest.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            descr=in.readLine();
            System.out.println("Valor recebido: ");
            System.out.println(descr);
            
        }catch(MalformedURLException e){
            System.out.println("Problema com a URL");
        }
        return descr;
    }
     
     public String receberNome(String key) throws UnsupportedEncodingException, IOException{
        Arquivo arquivo;
        String nome = null;
        try{
            URL addGetRequest = new URL("http://mytube-bd.appspot.com/serve?method=view&chave="+key);
            URLConnection conn = addGetRequest.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            nome=in.readLine();
            nome=in.readLine();
            System.out.println("Valor recebido: ");
            System.out.println(nome);
            
        }catch(MalformedURLException e){
            System.out.println("Problema com a URL");
        }
        return nome;
    }
}