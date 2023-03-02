package pidev.afarshop.Service.Mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MailService {

   /* @Value("${spring.mail.username}")
    private String from;*/

    @Autowired
    private JavaMailSender javaMailSender;

   /* /**
     * 发送简单的文本邮件
     * @param mailBean
     */
    /*public void sendSimpleMail(MailBean mailBean){
        log.info("L'envoi du courrier commence.:{}",mailBean.toString());
        try{
            SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
            simpleMailMessage.setTo(mailBean.getTo());
            simpleMailMessage.setSubject(mailBean.getSubject());
            simpleMailMessage.setText(mailBean.getContent());
            simpleMailMessage.setFrom(from);
            javaMailSender.send(simpleMailMessage);
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
        javaMailSender.send(message);
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
}
