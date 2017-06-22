/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.sun.mail.imap.IMAPMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
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
    
    public Mensagem()
    {
        
    }
    
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
    
    public String getRemetente() 
    {
        try 
        {
            /*Address[] add = this.msg.getFrom();
            String[] str = new String [add.length];*/
            Address[] froms = msg.getFrom();
            String enviador = froms == null ? null : ((InternetAddress) froms[0]).getAddress();
            
            /*for (int i = 0; i < add.length; i++)
                str[i] = add[i].toString();
            
            return str;*/
            return enviador;
        } 
        catch (MessagingException ex) 
        {
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
    
    public List<InputStream> getAttachments() throws Exception 
    {
        Object content = this.msg.getContent();
        
        if (content instanceof String)
            return null;        

        if (content instanceof Multipart) 
        {
            Multipart multipart = (Multipart) content;
            List<InputStream> result = new ArrayList<InputStream>();

            for (int i = 0; i < multipart.getCount(); i++)
                result.addAll(getAttachments(multipart.getBodyPart(i)));
            
            return result;

        }
        return null;
    }
    
    public List<String> getAttachmentsName() throws MessagingException, Exception
    {
        Object content = this.msg.getContent();
        
        if (content instanceof String)
            return null;        

        if (content instanceof Multipart) 
        {
            Multipart multipart = (Multipart) content;
            List<String> result = new ArrayList<String>();

            for (int i = 0; i < multipart.getCount(); i++)
            {
                Part part = multipart.getBodyPart(i);
                String name = part.getFileName();
                
                if (name == null)
                    continue;
                
                if (name.trim().isEmpty())
                    continue;               
                
                result.add(name);                
            }
            
            return result;
        }
        return null;
    }

    private List<InputStream> getAttachments(BodyPart part) throws Exception 
    {
        List<InputStream> result = new ArrayList<InputStream>();
        Object content = part.getContent();
        if (content instanceof InputStream || content instanceof String) 
        {
            boolean test = true;
            
            if (part.getFileName() == null)
                test = false;
            else    
                if (part.getFileName().trim().isEmpty())
                    test = false;
            
            if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition()) || test) 
            {
                result.add(part.getInputStream());
                return result;
            } 
            else 
            {
                return new ArrayList<InputStream>();
            }
        }

        if (content instanceof Multipart) 
        {
                Multipart multipart = (Multipart) content;
                for (int i = 0; i < multipart.getCount(); i++) 
                {
                    BodyPart bodyPart = multipart.getBodyPart(i);
                    result.addAll(getAttachments(bodyPart));
                }
        }
        return result;
    }

    public String getConteudo() 
    {
        try 
        {
            String result = "";
            Object content = this.msg.getContent();
            
            if (content instanceof Multipart) 
            {
                Multipart mp = (Multipart) content;
                for (int i = 0; i < mp.getCount(); i++) 
                {
                    BodyPart bp = mp.getBodyPart(i);
                    Object bpContent = bp.getContent();
                    
                    //É email com anexo
                    if (bpContent instanceof Multipart) 
                    {
                        mp = (Multipart) bpContent;
                        
                        for (int j = 0; j < mp.getCount(); j++)
                        {
                            bp = mp.getBodyPart(i);
                            bpContent = bp.getContent();
                            
                            if (Pattern
                            .compile(Pattern.quote("text/html"), Pattern.CASE_INSENSITIVE)
                            .matcher(bp.getContentType()).find()) 
                            {

                                result = (String) bpContent;
                            } 
                            else
                            {
                                result = (String) bpContent;
                            }
                        }
                        
                        break;
                    }    
                    else if (Pattern
                            .compile(Pattern.quote("text/html"), Pattern.CASE_INSENSITIVE)
                            .matcher(bp.getContentType()).find()) 
                    {

                        result = (String) bpContent;
                    } 
                    else
                    {
                        result = (String) bpContent;
                    }
                }
            }
            
            return result;
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
