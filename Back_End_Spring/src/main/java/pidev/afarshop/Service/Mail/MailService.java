package pidev.afarshop.Service.Mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pidev.afarshop.Entity.MailBean;

@Slf4j
@Service
public class MailService {

   /* @Value("${spring.mail.username}")
    private String from;*/

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;

   public void sendEmail(String to, String subject, String message) throws MessagingException {
       MimeMessage mimeMessage = mailSender.createMimeMessage();
       MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
       messageHelper.setSubject(subject);
       messageHelper.setTo(to);

       Context context = new Context();
       context.setVariable("subject", subject);
       context.setVariable("Body", message);
       String content = templateEngine.process("email-temp", context);


       messageHelper.setText(content, true);

       mailSender.send(mimeMessage);
   }

    public void sendWelcomeEmail(String to, String subject, String message) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
        messageHelper.setSubject(subject);
        messageHelper.setTo(to);

        Context context = new Context();
        context.setVariable("subject", subject);
        context.setVariable("message", message);
        //String content = templateEngine.process(System.getProperty("user.dir")+"/src/main/resources/templates/WelcomeMail.html", context);
        String content = templateEngine.process("welcomeMail", context);


        messageHelper.setText(content, true);
        mailSender.send(mimeMessage);
    }
    //token
   /* @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.mailSender = javaMailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email) {
        mailSender.send(email);
    }*/
}

