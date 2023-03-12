package pidev.afarshop.Controller.Mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import pidev.afarshop.Entity.MailBean;
import pidev.afarshop.Entity.Order1;
import pidev.afarshop.Entity.Product;
import pidev.afarshop.Entity.User;
import pidev.afarshop.Repository.ProductRepository;
import pidev.afarshop.Service.Mail.MailService;
import pidev.afarshop.Service.User.UserService;

import javax.mail.MessagingException;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Random;

@RequestMapping("/API/MAIL")
@RestController
@Slf4j
public class MailController {
     @Autowired
    private MailService senderService;
     @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;

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

   /* @Scheduled(fixedRate = 5000)
    private void dailyMail() throws MessagingException {
        List<User> users = userService.getAllUsers();
        log.info ("sending daily mail");
        for (User u : users){
            Random r = new Random();
            int high = u.getOrders().size()-1;
            log.error(String.valueOf(high));
            int result = high <= 0 ? 0: r.nextInt(high);
            Order1 order = u.getOrders().get(result);
            high = order.getProducts().size()-1;
            result = high <= 0 ? 0: r.nextInt(high);
            Product product = order.getProducts().get(result);
            List<Product> products = productRepository.findByCategory(product.getCategory());
            String to = u.getemail();
            String subject = "Checkout this ya boii";
            String message = null;
            Product p = null;
            if (product.equals(products.get(0))){
                high = order.getProducts().size()-1;
                result = high <= 0 ? 0: r.nextInt(high);
                p = products.get(result);
                message = "checkout "+p.getBrand();
            }else{
                p = products.get(0);
                message = "checkout "+p.getBrand();
            }
            senderService.sendEmail(to, subject, message);
        }
    }*/

}



