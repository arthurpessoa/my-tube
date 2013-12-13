/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package http;

/**
 *
 * @author master
 */
public class Arquivo {
    Long id;
    String chave;
    String dado;
    String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getDado() {
        return dado;
    }

    public void setDado(String dado) {
        this.dado = dado;
    }
    public Arquivo(Long id, String chave, String dado,String nome){
        this.id=id;
        this.chave=dado;
        this.dado=dado;
        this.nome=nome;
    }
    public Arquivo(){}
    
}
