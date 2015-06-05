package wto.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailMail
{
	public static void sendMessage(String email, String generatedId, String registredUsername, String appAddress) {

		final String username = "wtoimager@gmail.com";
		final String password = "space2001";
 
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
			String confirmationAddress = appAddress + "/confirm/" + generatedId;
			
			String text = "Hello " + registredUsername +"!"
					+ "<br/><br/>Thanks for registring an account on the world's best image hosting service!<br/><br/>"
					+ "You can activate your account by clicking on the following link: "
					+ "<a href=\"" + confirmationAddress + "\">" + confirmationAddress + "</a>";
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("wtoimager@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email));
			message.setSubject("WTO Imager Account Activation");
			message.setContent(text, "text/html; charset=utf-8");
 
			Transport.send(message);
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}