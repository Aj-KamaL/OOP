/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

/**
 *
 * @author kaust
 */
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

    public static void Rejected(String user,String a)
    {
        final String username = "classroombookingiiitd@gmail.com";
                final String password = "admintheboss";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
                        @Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("classroombookingiiitd@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(user));
			message.setSubject("Room booking request cancelled");
			message.setText("Hey,"+"\n"+"Your Room booking request was cancelled by Admin\n"+a
				+ "\n\n This is an auto generated mail.");

			Transport.send(message);

			System.out.println("Mail Sent");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
    }
    
    public static void Accepted(String user,String a)
    {
        final String username = "classroombookingiiitd@gmail.com";
                final String password = "admintheboss";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
                        @Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("classroombookingiiitd@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(user));
			message.setSubject("Room Booked");
			message.setText("Hey,"+"\n"+"Your Room booking request is approved by Admin\n"+a
				+ "\n\n This is an auto generated mail.");

			Transport.send(message);

			System.out.println("Mail Sent");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
    }
    
    public static void AutoRejected(String user,String a)
    {
        final String username = "classroombookingiiitd@gmail.com";
                final String password = "admintheboss";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
                        @Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("classroombookingiiitd@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(user));
			message.setSubject("Room booking request auto rejected");
			message.setText("Hey,"+"\n"+"Your Room booking request was Auto Rejected\n"+"Please contact Admin for more details.\n"+a
				+ "\n\n This is an auto generated mail.");

			Transport.send(message);

			System.out.println("Mail Sent");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
    }
    
	public static void main(String[] args) {

	}
}
