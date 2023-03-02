package pidev.afarshop.Controller.Mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pidev.afarshop.Entity.User;
import pidev.afarshop.Service.Mail.MailService;

import javax.mail.MessagingException;
@RestController
public class RegistrationController {
/*@Autowired
    private MailService notificationService;

    @Autowired
    private User user;

    /**
     *
     * @return
     */
/* @RequestMapping("send-mail")
    public String send() {

        /*
         * Creating a User with the help of User class that we have declared. Setting
         * the First,Last and Email address of the sender.
         */
  /*    user.setfirstname("oumaima");
        user.setlastname("Majdoub");
        user.setemail("oumaima.majdoub@esprit.tn");

        /*
         * Here we will call sendEmail() for Sending mail to the sender.
         */
  /*    try {
            notificationService.sendEmail(user);
        } catch (MailException mailException) {
            System.out.println(mailException);
        }
        return "Congratulations! Your mail has been send to the user.";
    }

    /**
     *
     * @return
     * @throws MessagingException
     */
  /*@RequestMapping("send-mail-attachment")
    public String sendWithAttachment() throws MessagingException {

        /*
         * Creating a User with the help of User class that we have declared. Setting
         * the First,Last and Email address of the sender.
         */
    /*  user.setfirstname("oumaima");
        user.setlastname("Majdoub");
        user.setemail("oumaima.majdoub@esprit.tn"); //Receiver's email address

        /*
         * Here we will call sendEmailWithAttachment() for Sending mail to the sender
         * that contains a attachment.
         */
     /* try {
            notificationService.sendEmailWithAttachment(user);
        } catch (MailException mailException) {
            System.out.println(mailException);
        }
        return "Congratulations! Your mail has been send to the user.";
    }*/
}
