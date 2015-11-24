package au.com.ibm.bluecoin.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ejb.Asynchronous;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.sendgrid.*;


public class SendMail {
	
	
	public void sendMail()
	{
		
		
		
		final String username = "adeelzafar@gmail.com";
		final String password = "s@mreeN84";

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
		
		
		
		
		// Recipient's email ID needs to be mentioned.
	      String to = "0430321919.ibm01@e2m.hutch.com.au";

	      // Sender's email ID needs to be mentioned
	      String from = "web@gmail.com";

	    
	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject("This is the Subject Line!");

	         // Now set the actual message
	         message.setText("This is actual message");

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }catch (Exception mex) {
	         mex.printStackTrace();
	      }
	}
	
	
   public void sendMailusingSendGrid(String message, String mobile)
	{
		
		    
		try
		{
			String emailAddress = mobile + ".ibm01@e2m.hutch.com.au";
			SendGrid sendgrid = new SendGrid("uqNzTeIcyp", "YSDAeiFfbobF7557");
			SendGrid.Email email = new SendGrid.Email();

		    email.addTo(emailAddress);
		    email.setFrom("adeel.zafar@gmail.com");
		    email.setSubject("..");
		    email.setHtml(message);
		    
		    System.out.println("Sending " + message + " to " + emailAddress);
		    
		    //email.setText(message);
		    SendGrid.Response response = sendgrid.send(email);
		
				    
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		
		
		
		
		
	}
	
	
	
	

}
