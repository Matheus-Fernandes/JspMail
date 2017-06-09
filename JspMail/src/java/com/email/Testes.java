/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.email;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;

/**
 *
 * @author u15184
 */
public class Testes {
    public static void check(String host, String storeType, String user,
      String password) 
    {           

        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");
        
        try {
          Session session = Session.getDefaultInstance(props, null);
          Store store = session.getStore("imaps");
          store.connect("imap.gmail.com", "marcioteste28@gmail.com", "@@123123");
          
          //create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();
            System.out.println("messages.length---" + messages.length);

            for (int i = 0, n = messages.length; i < n; i++) {
               Message message = messages[i];
               System.out.println("---------------------------------");
               System.out.println("Email Number " + (i + 1));
               System.out.println("Subject: " + message.getSubject());
               System.out.println("From: " + message.getFrom()[0]);
               System.out.println("Text: " + ((Multipart) message.getContent()).getBodyPart(0).getContent());

            }

            //close the store and folder objects
            emailFolder.close(false);
            store.close();
        } catch (NoSuchProviderException e) {
          e.printStackTrace();
          System.exit(1);
        } catch (MessagingException e) {
          e.printStackTrace();
          System.exit(2);
        } catch (IOException ex) {
            Logger.getLogger(Testes.class.getName()).log(Level.SEVERE, null, ex);
        }
   }

    public static void main(String[] args) {

       String host = "pop.gmail.com";// change accordingly
       String mailStoreType = "pop3";
       String username = "marcioteste28@gmail.com";// change accordingly
       String password = "@@123123";// change accordingly

       check(host, mailStoreType, username, password);

    }
}
