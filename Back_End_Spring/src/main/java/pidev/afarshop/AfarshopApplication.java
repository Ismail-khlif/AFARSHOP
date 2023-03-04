package pidev.afarshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import pidev.afarshop.Service.Mail.MailService;

import javax.mail.MessagingException;


@SpringBootApplication
@EntityScan(basePackages = {"pidev.afarshop.Entity"})
@ComponentScan(basePackages = {"pidev.afarshop.*"})
public class AfarshopApplication {
	public static void main(String[] args) {
		SpringApplication.run(AfarshopApplication.class, args);
	}


	}
