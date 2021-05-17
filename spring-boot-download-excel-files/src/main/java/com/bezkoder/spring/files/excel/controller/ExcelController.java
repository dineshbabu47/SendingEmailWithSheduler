package com.bezkoder.spring.files.excel.controller;



import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.bezkoder.spring.files.excel.service.ExcelService;
//import com.bezkoder.spring.files.mail.MailService;

@CrossOrigin("http://localhost:8081")
@Controller
@RequestMapping("/api/excel")
public class ExcelController {

	@Autowired
	private ExcelService fileService;
	
	

	@GetMapping("/download")
	//@Scheduled(fixedRate = 60000)
	public ResponseEntity<Resource> getFile()  {
		String filename = "tutorials.xlsx";
		InputStreamResource file = new InputStreamResource(fileService.load());
		
		/*
		 * Properties props = new Properties(); props.put("mail.smtp.auth", "true");
		 * props.put("mail.smtp.starttls.enable", "true");
		 * props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		 * props.put("mail.smtp.host", "smtp.gmail.com"); props.put("mail.smtp.port",
		 * "587");
		 * 
		 * Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		 * protected PasswordAuthentication getPasswordAuthentication() { return new
		 * PasswordAuthentication("mdineshbabumagi47@gmail.com", "d9655799067"); } });
		 * Message msg = new MimeMessage(session); msg.setFrom(new
		 * InternetAddress("tutorialspoint@gmail.com", false));
		 * 
		 * msg.setRecipients(Message.RecipientType.TO,
		 * InternetAddress.parse("mdineshbabumagi47@gmail.com"));
		 * msg.setSubject("Tutorials point email");
		 * msg.setContent("Tutorials point email", "text/html"); msg.setSentDate(new
		 * Date());
		 * 
		 * MimeBodyPart messageBodyPart = new MimeBodyPart();
		 * messageBodyPart.setContent("Tutorials point email", "text/html");
		 * 
		 * Multipart multipart = new MimeMultipart();
		 * multipart.addBodyPart(messageBodyPart); MimeBodyPart attachPart = new
		 * MimeBodyPart();
		 * 
		 * attachPart.attachFile("F:\\IMG-20210228-WA0007.jpg");
		 * multipart.addBodyPart(attachPart); msg.setContent(multipart);
		 * Transport.send(msg);
		 */

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
	}

}
