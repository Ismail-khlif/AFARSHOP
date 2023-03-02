package pidev.afarshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import pidev.afarshop.Service.Mail.MailService;

import javax.mail.MessagingException;


@SpringBootApplication
public class AfarshopApplication {
	public static void main(String[] args) {
		SpringApplication.run(AfarshopApplication.class, args);
	}


	}
