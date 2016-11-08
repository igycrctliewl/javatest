/*
 *
 */

import java.io.*;
import java.net.InetAddress;
import java.util.Properties;
import java.util.Date;

import javax.mail.*;

import javax.mail.internet.*;

import com.sun.mail.smtp.*;


public class Distribution {

    public static void main(String args[]) throws Exception {
        Properties props = System.getProperties();
        String p = props.getProperty("mail.smtps.host");
        System.out.println("mail.smtps.host : " + p );
//        props.put("mail.smtps.auth","true");
/*
        props.put("mail.smtps.host","smtp.gmail.com");
        props.put("mail.smtps.auth","true");
        Session session = Session.getInstance(props, null);
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("fromAddr@trinet.com"));;
        msg.setRecipients(Message.RecipientType.TO,
        InternetAddress.parse("michael.brothers@trinet.com", false));
        msg.setSubject("Test message "+System.currentTimeMillis());
        msg.setText("Vistory!  The City Goes Wild.");
        msg.setHeader("X-Mailer", "ErrorNotification");
        msg.setSentDate(new Date());
        SMTPTransport t =
            (SMTPTransport)session.getTransport("smtps");
        t.connect("smtp.gmail.com", "admin@tovare.com", "<insert password here>");
        t.sendMessage(msg, msg.getAllRecipients());
        System.out.println("Response: " + t.getLastServerResponse());
        t.close();
*/
    }
}