package pidev.afarshop.Service.Mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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

   /* /**
     * 发送简单的文本邮件
     * @param mailBean
     */
   /* public void sendSimpleMail(MailBean mailBean){
        log.info("L'envoi du courrier commence.:{}",mailBean.toString());
        try{
            SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
            simpleMailMessage.setTo(mailBean.getToEmail());
            simpleMailMessage.setSubject(mailBean.getSubject());
            simpleMailMessage.setText(mailBean.getBody());
            mailSender.send(simpleMailMessage);
            log.info("E-mail envoyé avec succès!");
        }catch (Exception e){
            log.error("Échec de l'envoi du courrier:{}",e.getMessage());
        }
    }*/
   public void sendSimpleEmail(String toEmail,
                                String subject,
                                String body
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("oumaima.majdoub@esprit.tn");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail Send...");


    }

   /* /**
     * 发送HTML格式文件
     * @param mailBean
     * @throws MessagingException
     */
   /* public void  sendHtmlMessage(MailBean mailBean) {
        log.info("\n" + "L'envoi du courrier commence. :{}",mailBean.toString());
        try{
            MimeMessage mimeMessage=javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setTo(mailBean.getTo());
            mimeMessageHelper.setSubject(mailBean.getSubject());
            mimeMessageHelper.setText(mailBean.getContent(),true);
            mimeMessageHelper.setFrom(from);
            javaMailSender.send(mimeMessage);
            log.info("E-mail envoyé avec succès!");
        }catch (Exception e){
            log.error("Échec de l'envoi du courrier:{}",e.getMessage());
        }
    }

    /**
     *发送带有附件的邮件
     * @param mailBean
     */
    /*public void sendAttachMentMessage(MailBean mailBean){
        log.info("\n" + "L'envoi du courrier commence.:{}",mailBean.toString());
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(mailBean.getTo());
            mimeMessageHelper.setSubject(mailBean.getSubject());
            mimeMessageHelper.setText(mailBean.getContent(),true);
            mimeMessageHelper.setFrom(from);
            //准备附件相关
            List<String> paths=mailBean.getAttachmentPath();
            for (String path:paths) {
                FileSystemResource file= new FileSystemResource(new File(path));
                String fileName = file.getFilename();
                mimeMessageHelper.addAttachment(fileName,file);
            }
            javaMailSender.send(mimeMessage);
            log.info("E-mail envoyé avec succès!");
        }catch (Exception e){
            log.error("Échec:{}",e.getMessage());
        }
    }

    /**
     * 发送带有图片的邮件
     * @param mailBean
     */
    /*public void sendImageMessage(MailBean mailBean){
        log.info("L'envoi du courrier commence:{}",mailBean.toString());
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(mailBean.getTo());
            mimeMessageHelper.setSubject(mailBean.getSubject());
            mimeMessageHelper.setText(mailBean.getContent(),true);
            mimeMessageHelper.setFrom(from);
            FileSystemResource file= new FileSystemResource(new File(mailBean.getImagePath()));
            mimeMessageHelper.addInline(mailBean.getRscId(),file);
            javaMailSender.send(mimeMessage);
            log.info("E-mail envoyé avec succès!");
        }catch (Exception e){
            log.error("Échec:{}",e.getMessage());
        }
    }
*/
   public void sendEmail(String to, String subject, String message) throws MessagingException {
       MimeMessage mimeMessage = mailSender.createMimeMessage();
       MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
       messageHelper.setSubject(subject);
       messageHelper.setTo(to);

       Context context = new Context();
       context.setVariable("subject", subject);
       context.setVariable("Body", message);
       //String content = templateEngine.process("email-template", context);
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
        String content = templateEngine.process(System.getProperty("user.dir")+"/src/main/resources/templates/email-temp.html", context);
       // String content = templateEngine.process("welcomeMail", context);


        messageHelper.setText(content, true);
        mailSender.send(mimeMessage);
    }
}

