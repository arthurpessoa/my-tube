/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projsd;
import http.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
public class ProjSD {

    /**
     * @param args the command line arguments
     */
    /*
    public static void main(String[] args) throws UnsupportedEncodingException, IOException {
        Arquivo arquivo = new Arquivo();
        Client cliente = new Client();
        String chave = cliente.newKey();
        System.out.println(chave);
        if(cliente.keyCheck(chave)){
            System.out.println("Filme existe");
            String descr = "legalz ae";
            String nome = "Titanic";
            String dado = URLEncoder.encode(descr, "UTF-8");
            String key = URLEncoder.encode(chave, "UTF-8");
            String name = URLEncoder.encode(nome, "UTF-8");
            arquivo.setDado(dado);
            arquivo.setChave(key);
            arquivo.setNome(name);
            
            Manip upDown = new Manip();
            upDown.enviarArquivo(arquivo);
            upDown.receberDescr(key);
            upDown.receberNome(key);
        }else{
            System.out.println("Filme nao existe");
        }
    }
    */
}
