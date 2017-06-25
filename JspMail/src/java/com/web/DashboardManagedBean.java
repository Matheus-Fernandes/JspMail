/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web;

import com.email.EmailProvider;
import com.email.exception.InvalidPageException;
import com.model.Mensagem;
import com.jdbc.Email;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;

/**
 *
 * @author Matheus
 */
public class DashboardManagedBean 
{
    private Store store;
    private Folder folder;
    private Mensagem[] mensagens;
    private EmailProvider ep;
    private final int MENSAGENS_PAGINA = 20;

    
    public void setEmail(Email email)
    {
        try 
        {            
            this.ep = new EmailProvider(email);
            this.store = this.ep.getStore();
            this.folder = this.store.getFolder("INBOX");
            this.getCaixaEntrada(1);

        } 
        catch (MessagingException ex) 
        {
            Logger.getLogger(DashboardManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public DashboardManagedBean()
    {

    }
    
    public Mensagem[] getCaixaEntrada(int pagina)
    {    
        Mensagem[] ret = null;
        
        try 
        {
            Message[] msg = this.ep.getMessagePage(this.folder, Folder.READ_WRITE, pagina, MENSAGENS_PAGINA);   
            ret = new Mensagem[msg.length];
            
            for (int i = 0; i < ret.length; i++)
                ret[i] = new Mensagem(pagina * MENSAGENS_PAGINA + i + 1, msg[i]);
            
            this.mensagens = ret;
        } 
        catch (InvalidPageException ex) 
        {
            Logger.getLogger(DashboardManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        return ret;
    }
    
    public Mensagem getMensagem(int id){
        for (Mensagem mensagem : this.mensagens){
            if (mensagem.getId() == id)
                return mensagem;
        }
        
        return null;
    }
    
    public int countMensagens(){
        return mensagens.length;
    }
    
    public boolean paginaValida(int pagina){
        int inicio = pagina * MENSAGENS_PAGINA + 1;
        
        return pagina >= 0 && inicio <= mensagens.length;
    }
    
    public int inicio(int pagina){
        int inicio = pagina * MENSAGENS_PAGINA + 1;
        
        if (inicio > mensagens.length)
            return mensagens.length;
        
        return inicio;
    }
    
    public int fim (int pagina){
        int fim = inicio(pagina) + MENSAGENS_PAGINA - 1;;
        
        if (fim > mensagens.length)
            return mensagens.length;
        
        return fim;
    }
}
