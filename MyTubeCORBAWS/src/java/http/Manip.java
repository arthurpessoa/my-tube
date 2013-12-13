/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author master
 */
public class Manip {
    
    public void enviarArquivo(Arquivo arquivo) throws UnsupportedEncodingException, MalformedURLException, IOException{
        try{
            URL url = new URL("http://mytube-bd.appspot.com/upload");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write("chave=" + arquivo.chave + "&dado=" + arquivo.dado + "&nome=" + arquivo.nome);
            writer.close();
            System.out.println(arquivo.dado);
            System.out.println(arquivo.chave);
             System.out.println(arquivo.nome);
            //verificação se ocorreu com sucesso
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                System.out.println("Enviado para cloud!");
            } else {
                System.out.println("Erro ao enviar para cloud!");
            }
        }catch(MalformedURLException e){
            System.out.println("Algum problema com a URL");
        }
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
