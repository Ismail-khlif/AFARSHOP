package pidev.afarshop.Entity;

import lombok.*;

import java.io.Serializable;
import java.util.List;
@Setter
@Getter
@ToString
@Data
@Builder
public class MailBean implements Serializable {
    private  String toEmail;
    private List<String> cc;
    private  String from;
    private  String subject;
    private  String Body;


}
