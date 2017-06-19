package com.email;

import com.email.exception.InvalidPageException;
import javax.mail.Message;

import com.jdbc.Email;
import com.model.Mensagem;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Flags;
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
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailProvider 
{        
    private Email currentEmail;
    
    private static final int DEFAULT_PAGE_SIZE = 10;
    
    public EmailProvider()
    {
        
    }
    
    public void setEmail(Email email)
    {
        this.currentEmail = email;
    }
    
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
    
    public int getMessageCount(Folder folder) throws MessagingException
    {
        return folder.getMessageCount();
    }
      
    public Message[] getMessagePage(Folder folder, int readMode, int page, int pageSize) throws InvalidPageException
    {
        Message[] messages = null;
        
        if (page <= 0)
            throw new InvalidPageException(page);
        
        try 
        {
            if (folder.isOpen() == false)
                folder.open(readMode);
            
            int total = folder.getMessageCount();           

            if (total == 0)
                return new Message[0];                       
            
            if (total < (page - 1) * pageSize)
                throw new InvalidPageException(page);
            

            Message[] toGet;
            
            if (total > pageSize)
                toGet = new Message[pageSize];            
            else 
                toGet = new Message[total];
            
            messages = folder.getMessages();
            
            int start = total - ((page - 1) * pageSize) - 1;            
            for (int i = 0; i < toGet.length; ++i)            
                toGet[i] = messages[start - i];
            
            return toGet;          
        } 
        catch (MessagingException ex) 
        {
            Logger.getLogger(EmailProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return messages;
    }
    
    public boolean deleteMessage(Message message, boolean autoClose)
    {
        try 
        {
            message.setFlag(Flags.Flag.DELETED, true);
            
            if (autoClose)
                message.getFolder().close(true);
            
            return true;
        } 
        catch (MessagingException ex) 
        {
            Logger.getLogger(EmailProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean sendMessage(String subject, String[] recipients, String content, DataSource attachment)
    {
        try 
        {
            Session session = this.getSMTPSession();
            
            InternetAddress[] addresses = new InternetAddress[recipients.length];
            for (int i = 0; i < addresses.length; i++)            
                addresses[i] = new InternetAddress(recipients[i]);            
                        
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.currentEmail.getEmailPrincipal())); 
            message.setSubject(subject);
            message.setRecipients(Message.RecipientType.TO, addresses);
            
            Multipart mp = new MimeMultipart();
            
            MimeBodyPart bodypart = new MimeBodyPart();
            bodypart.setText(content, "utf-8");            
    

            mp.addBodyPart(bodypart);
            message.setContent(mp);
            
            
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
            
            /*
            String[] rec = { "marcioazjunior@gmail.com" };
            provider.sendMessage(provider.getSMTPSession(), "Assunto", rec, "Mensagem enviada pelo JavaMail", null);
            */
                       
            
            Store store = provider.getStore();
            Folder folder =  store.getFolder("INBOX");
           // Folder[] subFolders = store.getDefaultFolder().list("*");
            
            
            Message[] messages = provider.getMessagePage(folder, Folder.READ_WRITE, 1, DEFAULT_PAGE_SIZE);
            
            com.model.Mensagem msg = new Mensagem(1, messages[0]);
            List<String> lista = msg.getAttachmentsName();
            List<InputStream> arq = msg.getAttachments();
            
            for (int i = 0; i < lista.size(); i++)
            {
                InputStream is = arq.get(i);
                File f = new File("C:\\Temp\\" + lista.get(i));
                FileOutputStream fos = new FileOutputStream(f);
                byte[] buf = new byte[4096];
                int bytesRead;
                while((bytesRead = is.read(buf))!=-1) {
                    fos.write(buf, 0, bytesRead);
                }
                fos.close();
            }               
            
            /*
            for (int i = 0, n = messages.length; i < n; i++) {
                Message message = messages[i];
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Text: " + ((Multipart) message.getContent()).getBodyPart(0).getContent());

             }
            */
            //provider.deleteMessage(messages[0], false);

            //close the store and folder objects
            folder.close(true);
            store.close();
        } catch (Exception ex) {
            Logger.getLogger(EmailProvider.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

