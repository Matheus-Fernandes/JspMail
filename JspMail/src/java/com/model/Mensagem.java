/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author Matheus
 */
public class Mensagem 
{
    public int id;
    private Message msg;
    
    public Mensagem(int id, Message msg)
    {
        this.id = id;
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    public String[] getDestinatarios() 
    {
        try 
        {
            Address[] add = this.msg.getRecipients(Message.RecipientType.TO);
            String[] str = new String [add.length];
            
            for (int i = 0; i < add.length; i++)
                str[i] = add[i].toString();
            
            return str;
        } 
        catch (MessagingException ex) 
        {
            Logger.getLogger(Mensagem.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }


    public String getRemetente() {
        try {
            return Arrays.toString(this.msg.getFrom());
        } catch (MessagingException ex) {
            Logger.getLogger(Mensagem.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void addRemetente(String remetente) {
        try {
            Address[] add = { new InternetAddress(remetente) };
            this.msg.addFrom(add);
        } catch (AddressException ex) {
            Logger.getLogger(Mensagem.class.getName()).log(Level.SEVERE, null, ex);            
        } catch (MessagingException ex) {
            Logger.getLogger(Mensagem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getAssunto() {
        try {
            return this.msg.getSubject();
        } catch (MessagingException ex) {
            Logger.getLogger(Mensagem.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void setAssunto(String assunto) {
        try {
            this.msg.setSubject(assunto);
        } catch (MessagingException ex) {
            Logger.getLogger(Mensagem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getConteudo() 
    {
        try 
        {
            Multipart mp = (Multipart) this.msg.getContent();
            return (String) mp.getBodyPart(0).getContent();
        } 
        catch (IOException ex) {
            Logger.getLogger(Mensagem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Mensagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    public void setConteudo(String conteudo) {
        try {
            this.msg.setText(conteudo);
        } catch (MessagingException ex) {
            Logger.getLogger(Mensagem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return "Mensagem{" + "id=" + id + ", destinatario=" + Arrays.toString(this.getDestinatarios()) + ", remetente=" + this.getRemetente() + ", assunto=" + this.getAssunto() + ", conteudo=" + this.getConteudo() + '}';
    }
    
    
}
