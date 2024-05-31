package com.dailycodebuffer.email;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendSimpleEmail(String to, String body, String subject) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("javaprogrammer204@gmail.com");
		message.setTo(to);
		message.setText(body);
		message.setSubject(subject);
		
		javaMailSender.send(message);
		System.out.println("Mail send");
		
	}
	
	public void sendEmailwithAttachment(String to, String body, String subject, String attachment) throws MessagingException {
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
		mimeMessageHelper.setFrom("javaprogrammer204@gmail.com");
		mimeMessageHelper.setTo(to);
		mimeMessageHelper.setText(body);
		mimeMessageHelper.setSubject(subject);
		
		FileSystemResource fileSystemResource = new FileSystemResource(new File(attachment));
		mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
		javaMailSender.send(mimeMessage);
		System.out.println("Mail send");
	}

}
