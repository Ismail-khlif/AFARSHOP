package pidev.afarshop.Controller.Mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pidev.afarshop.Entity.MailBean;
import pidev.afarshop.Service.Mail.MailService;
import javax.mail.MessagingException;

import javax.mail.MessagingException;

@RequestMapping("/mail")
@RestController
public class MailController {
     @Autowired
    private MailService senderService;

      @PostMapping("/simple")
        public ResponseEntity<String> sendEmail(@RequestBody MailBean mailBean) {
          /*try {*/
          senderService.sendSimpleEmail(mailBean.getSubject(),mailBean.getBody(),mailBean.getToEmail());
            return ResponseEntity.ok("Email envoyé avec succès!");
          /*} catch (MessagingException e) {
              e.printStackTrace();
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email.");
          }*/
        }

        /*@PostMapping("/html")
        public ResponseEntity<String> sendHtmlMail(@RequestBody MailBean mailBean) {
            mailService.sendHtmlMessage(mailBean);
            return ResponseEntity.ok("Email envoyé avec succès!");
        }

        @PostMapping("/attachment")
        public ResponseEntity<String> sendAttachmentMail(@RequestBody MailBean mailBean) {
            mailService.sendAttachMentMessage(mailBean);
            return ResponseEntity.ok("Email envoyé avec succès!");
        }

        @PostMapping("/image")
        public ResponseEntity<String> sendImageMail(@RequestBody MailBean mailBean) {
            mailService.sendImageMessage(mailBean);
            return ResponseEntity.ok("Email envoyé avec succès!");
        }
*/
    }

