/*
/*  thanks http://www.tutorialspoint.com/javamail_api/javamail_api_sending_simple_email.htm
/**/

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
   public static void main(String[] args) {
      // Recipient's email ID needs to be mentioned.
      String to = "michael.brothers@trinet.com";

      // Sender's email ID needs to be mentioned
      String from = "doNotReply@trinet.com";

      // not needed for TriNet.  IP of requesting machine must be whitelisted
      final String username = "";
      final String password = "";

      
      String host = "mailrelay.trinet.com";

      Properties props = new Properties();
      //these first two are not needed for TriNet
      //props.put("mail.smtp.auth", "true");
      //props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "25");

      
      System.out.println("Ready to create session...");
      
      // Get the Session object.
      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
	   }
         });

      try {
	   // Create a default MimeMessage object.
	   Message message = new MimeMessage(session);
	
	   // Set From: header field of the header.
	   message.setFrom(new InternetAddress(from));
	
	   // Set To: header field of the header.
	   message.setRecipients(Message.RecipientType.TO,
               InternetAddress.parse(to));
	
	   // Set Subject: header field
	   message.setSubject("Victory! The City Goes Wild.");
	
	   // Now set the actual message
	   message.setText("The people of San Francisco came together in a giddy triumph yesterday, surging out of homes " + 
                       "and bars in wild celebration of the 49ers' Super Bowl victory - the city's first professional sports championship.");

	   // Send message
       System.out.println("Ready to send message...");
	   Transport.send(message);

	   System.out.println("Sent message successfully....");

      } catch (MessagingException e) {
         System.out.println("Message failed");
         throw new RuntimeException(e);
      }
   }
}