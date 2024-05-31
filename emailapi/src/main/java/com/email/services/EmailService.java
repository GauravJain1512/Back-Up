package com.email.services;



import org.apache.logging.log4j.message.Message;
import org.springframework.boot.rsocket.server.RSocketServer.Transport;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	public void sendEmail(String subject, String message, String to) {
		
	
	    	//variable for gmail host
			
	    			String host="smtp.gmail.com";
	    			
	    			// get the system properties
	    			Process properties = System.getProperties();
	    			System.out.println("Properties: "+properties);
	    			
	    			//setting important to properties object
	    			properties.put("mail.smtp.host", host);
	    			properties.put("mail.smtp.port", "465");
	    			properties.put("mail.smtp.ssl.enable", "true");
	    			properties.put("mail.smtp.ssl.auth", "true");
	    			
	    			//Step 1 : to get the session object
	    		
	    			Session session = Session.getInstance(properties, new Authenticator() {

	    				@Override
	    				protected PasswordAuthentication getPasswordAuthentication() {
	    					
	    					return new PasswordAuthentication("javaprogrammer204@gmail.com", "asdcxz123@");
	    				}
	    				
	    			});
	    			
	    			session.setDebug(true);
	    			// step 2 : compose the message [ text, multi-media]
	    			MimeMessage mimeMessage = new MimeMessage(session);
	    			
	    			try {
	    			// from
	    			mimeMessage.setFrom(from);
	    			// adding recipient to message
	    			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	    			
	    			// adding subject
	    			mimeMessage.setSubject(subject);
	    			// attachment
	    			 String path = "C:\\Users\\DJ\\Desktop\\membership.png";
	    			 
	    			 MimeMultipart mimeMultipart = new MimeMultipart();
	    			 
	    			 MimeBodyPart textMime = new MimeBodyPart();
	    			 
	    			 MimeBodyPart fileMime = new MimeBodyPart();
	    			 
	    			 textMime.setText(message);
	    			 
	    			 File file = new File(path);
	    			 fileMime.attachFile(file);
	    			 
	    			 mimeMultipart.addBodyPart(textMime);
	    			 mimeMultipart.addBodyPart(fileMime);
	    			 
	    			 mimeMessage.setContent(mimeMultipart);
	    			
	    			// step : 3  send message using transport class
	    			Transport.send(mimeMessage);
	    			System.out.println("Send Successfully...");
	    			}catch (Exception e) {
	    			e.printStackTrace();
	    			}
	}
}
