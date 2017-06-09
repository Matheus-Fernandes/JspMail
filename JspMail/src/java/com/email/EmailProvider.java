package com.email;

import javax.mail.Message;

import com.jdbc.Email;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;

public class EmailProvider 
{        
    private Email currentEmail;
    
    private static final int DEFAULT_PAGE_SIZE = 10;
    
    public EmailProvider(Email email)
    {
        this.currentEmail = email;
    }
    
    public Store getStore()
    {
        try 
        {
            //create properties field
            Properties props = System.getProperties();
            props.setProperty("mail.store.protocol", "imaps");

            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com",  this.currentEmail.getOutroEmail(), this.currentEmail.getSenha());            
            
            return store;
        } 
        catch (NoSuchProviderException ex) 
        {
            Logger.getLogger(EmailProvider.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (MessagingException ex) 
        {
            Logger.getLogger(EmailProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public Message[] getAllMessages(Folder folder, int page, int amount)
    {
        Message[] messages = null;
        
        try 
        {
            folder.open(Folder.READ_ONLY);
            try 
            {
                messages = folder.getMessages(page * amount + 1, (page + 1) * amount);
            }
            catch (Exception e)
            {
                messages = folder.getMessages();                
            }
            
            return messages;          
        } 
        catch (MessagingException ex) 
        {
            Logger.getLogger(EmailProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return messages;
    }
    
     public static void main(String[] args) {

        try {
            Email email = new Email("aaa", "marcioteste28@gmail.com", "@@123123");    
            email.setHostRecebimento("pop.gmail.com");
            email.setPortaRecebimento(995);
            EmailProvider provider = new EmailProvider(email);
                       
            
            Store store = provider.getStore();
            Folder folder = store.getFolder("INBOX");
            
            Message[] messages = provider.getAllMessages(folder, 0, DEFAULT_PAGE_SIZE);
            
            for (int i = 0, n = messages.length; i < n; i++) {
                Message message = messages[i];
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Text: " + ((Multipart) message.getContent()).getBodyPart(0).getContent());

             }

            //close the store and folder objects
            folder.close(false);
            store.close();
        } catch (Exception ex) {
            Logger.getLogger(EmailProvider.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

