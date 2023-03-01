package pidev.afarshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pidev.afarshop.Entity.Mail;
import pidev.afarshop.Service.Email.EmailService;

@EnableWebMvc
@SpringBootApplication
public class AfarshopApplication {
    @Autowired
	EmailService emailService;
	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	public static void main(String[] args) {
		SpringApplication.run(AfarshopApplication.class, args);
	}
	@EventListener(ApplicationReadyEvent.class)
	public void sendCv() {
		Mail mail = new Mail();
		mail.setHeader("CV");
		mail.setTo("oumaimamjb@gmail.com");
		mail.setText("Success");
		emailService.sendMessageByMail(mail);
		System.out.println("Ok");

	}
}