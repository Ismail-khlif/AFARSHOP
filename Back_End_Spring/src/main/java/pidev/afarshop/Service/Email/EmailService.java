package pidev.afarshop.Service.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pidev.afarshop.Entity.Mail;

@Service
public class EmailService {
     JavaMailSender javaMailSender;
    @Autowired
    public  EmailService(JavaMailSender javaMailSender)  {
        this.javaMailSender = javaMailSender;
    }
    public void sendMessageByMail(Mail mail){
      SimpleMailMessage simpleMailMessage=new SimpleMailMessage() ;
      simpleMailMessage.setFrom("oumaima.majdoub@esprit.tn");
      simpleMailMessage.setTo(mail.getTo());
      simpleMailMessage.setSubject(mail.getHeader());
      simpleMailMessage.setText(mail.getText());
      javaMailSender.send(simpleMailMessage);
    }
}
