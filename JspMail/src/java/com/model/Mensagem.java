/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

/**
 *
 * @author Matheus
 */
public class Mensagem {
    public int id;
    public String destinatario;
    public String remetente;
    
    public String assunto;
    public String conteudo;

    public Mensagem(int id, String destinatario, String remetente, String assunto, String conteudo) {
        this.id = id;
        this.destinatario = destinatario;
        this.remetente = remetente;
        this.assunto = assunto;
        this.conteudo = conteudo;
    }
    
    public Mensagem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    @Override
    public String toString() {
        return "Mensagem{" + "id=" + id + ", destinatario=" + destinatario + ", remetente=" + remetente + ", assunto=" + assunto + ", conteudo=" + conteudo + '}';
    }
    
    
}
