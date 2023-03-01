package pidev.afarshop.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
public class Mail  {

    private String header;
    private String text;
    private String to;

    public Mail(){

    }
    public void setHeader(String header){
        this.header=header;
    }
    public String getHeader(){
        return header;
    }
    public void setText(String text){
        this.text=text;
    }
    public String getText(){
        return text;
    }
    public void setTo(String To){
        this.to=to;
    }
    public String getTo(){
        return to;
    }



}
