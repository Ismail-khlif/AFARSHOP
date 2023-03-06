package pidev.afarshop.Controller.Mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidev.afarshop.Entity.MailBean;
import pidev.afarshop.Entity.User;
import pidev.afarshop.Service.Mail.MailService;
import pidev.afarshop.Service.User.UserService;

import javax.mail.MessagingException;

import javax.mail.MessagingException;

@RequestMapping("/API/MAIL")
@RestController
public class MailController {
     @Autowired
    private MailService senderService;
    private UserService userService;

    @GetMapping("/get")
    public String hello(){
        return "hello world";
    }


    @PostMapping(value="/send")
    public ResponseEntity<String> sendMail() throws MessagingException {
        String to = "oumaima.mjb@gmail.com";
        String subject = "Hello WE ARE AFARSHOP";
        String message = "This is a test email sent from a Spring Boot application using Thymeleaf templates.";
        senderService.sendEmail(to, subject, message);
        return ResponseEntity.ok("Mail sending");
    }

    @PostMapping("/{mail}")
    public ResponseEntity<String> sendMailToOne(@PathVariable("mail") String m) throws MessagingException {
        String to = m;
        String subject = "Hello from Spring Boot";
        String message = "This is a test email sent from a Spring Boot application using Thymeleaf templates.";
        senderService.sendEmail(to, subject, message);
        return ResponseEntity.ok("Mail sending to "+m);
    }

    @PostMapping("/sub/{mail}/{sub}")
    public ResponseEntity<String> sendMailToOneWithSub(@PathVariable("mail") String m,@PathVariable("sub") String s) throws MessagingException {
        String to = m;
        String subject = s;
        String message = "This is a test email sent from a Spring Boot application using Thymeleaf templates.";
        senderService.sendEmail(to, subject, message);
        return ResponseEntity.ok("Mail sending to "+m);
    }

    @PostMapping("/welcome/{mail}/{id}")
    public String sendWelcome(@PathVariable("mail") String m,@PathVariable("id") Long id) throws MessagingException {
        //User u = userService.getUserById(id).get();
        String mes = "test";
        senderService.sendWelcomeEmail(m,"HELLO",mes);
        return "it's okey";
    }
}



