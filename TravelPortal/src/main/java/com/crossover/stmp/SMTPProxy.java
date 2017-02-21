package com.crossover.stmp;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SMTPProxy {
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
	static final String username = "rp.xover@gmail.com";
    static final String password = "passwd12345";
    
	public static void main(String args[]){
		sendMail("rafael.pino@gmail.com", "Prueba");
	}
    public static void sendMail(String recipient, String body){

         Properties props = new Properties();
         props.put("mail.smtp.auth", "true");
         props.put("mail.smtp.starttls.enable", "true");
         props.put("mail.smtp.host", "smtp.gmail.com");
         props.put("mail.smtp.port", "587");

         Session session = Session.getInstance(props,
           new javax.mail.Authenticator() {
             protected PasswordAuthentication getPasswordAuthentication() {
                 return new PasswordAuthentication(username, password);
             }
           });

         try {

             Message message = new MimeMessage(session);
             message.setContent(body, "text/html; charset=utf-8");
             message.setFrom(new InternetAddress("travelApp@crossover.com"));
             message.setRecipients(Message.RecipientType.TO,
                 InternetAddress.parse(recipient));
             message.setSubject("Your tickets");
             //message.setText(body);

             Transport.send(message);

             System.out.println("Done");

         } catch (MessagingException e) {
             throw new RuntimeException(e);
         }
    }
}
