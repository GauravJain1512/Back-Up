package com.dailycodebuffer.email;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmailHelper {

	@Autowired
	private EmailSenderService emailSenderService;
	
	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException {
	//	this.emailSenderService.sendSimpleEmail("javaprogrammer204@gmail.com", "This is body", "This is subject");
		this.emailSenderService.sendEmailwithAttachment("nj131611@gmail.com", "This is body", "Email with Attcahment", "D:\\CDAC\\JAVA\\Spring boot By Durgesh\\ScreenShot\\A.PNG");
	}
}
