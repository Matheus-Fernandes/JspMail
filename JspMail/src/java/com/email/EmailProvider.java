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
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailProvider 
{        
    private Email currentEmail;
    
    private static final int DEFAULT_PAGE_SIZE = 10;
    
    public EmailProvider(Email email)
    {
        this.currentEmail = email;
    }
    
    public Session getSMTPSession()
    {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", this.currentEmail.getServidorEnvio());
        props.put("mail.smtp.port", this.currentEmail.getPortaEnvio()); //587

        Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(currentEmail.getOutroEmail(), currentEmail.getSenha());
                        }
                    });
        
        return session;        
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
            store.connect(this.currentEmail.getServidorRecebimento(),  this.currentEmail.getOutroEmail(), this.currentEmail.getSenha());            
            
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
    
    public Message[] getMessagePage(Folder folder, int page, int amount)
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
                try
                {
                    messages = folder.getMessages(page * amount + 1, folder.getMessageCount());       
                }
                catch (Exception f)
                {
                    f.printStackTrace(System.err);
                }
            }
            
            return messages;          
        } 
        catch (MessagingException ex) 
        {
            Logger.getLogger(EmailProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return messages;
    }
    
    public boolean sendMessage(Session session, String subject, String[] recipients, String content, DataSource attachment)
    {
        try 
        {
            InternetAddress[] addresses = new InternetAddress[recipients.length];
            for (int i = 0; i < addresses.length; i++)            
                addresses[i] = new InternetAddress(recipients[i]);            
            
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.currentEmail.getEmailPrincipal())); 
            message.setSubject(subject);
            message.setRecipients(Message.RecipientType.TO, addresses);
            message.setText(content);
            
            Transport.send(message);
            return true;
        }         
        catch (AddressException ex) 
        {
            Logger.getLogger(EmailProvider.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (MessagingException ex) 
        {
            Logger.getLogger(EmailProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static void main(String[] args) 
    {

        try {
            Email email = new Email();
            email.setEmailPrincipal("aaa");    
            email.setOutroEmail("marcioteste28@gmail.com");
            email.setSenha("@@123123");
            email.setServidorRecebimento("imap.gmail.com");
            email.setPortaRecebimento(993);
            email.setServidorEnvio("smtp.gmail.com");
            email.setPortaEnvio(587);
            
            EmailProvider provider = new EmailProvider(email);
            String[] rec = { "marcioazjunior@gmail.com" };
            provider.sendMessage(provider.getSMTPSession(), "Assunto", rec, "Mensagem enviada pelo JavaMail", null);
                       
        /*    
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
            store.close();*/
        } catch (Exception ex) {
            Logger.getLogger(EmailProvider.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

