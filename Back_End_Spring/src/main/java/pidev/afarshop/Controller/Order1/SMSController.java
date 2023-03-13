package pidev.afarshop.Controller.Order1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import pidev.afarshop.Entity.SmsPojo;
import pidev.afarshop.Service.Order1.SmsS1ervice;

@RestController
@AllArgsConstructor
@RequestMapping("/api/Order1/SMS/")
public class SMSController {

    @Autowired
    SmsS1ervice smsService;

    @Autowired
    private SimpMessagingTemplate webSocket;

    private final String  TOPIC_DESTINATION = "/lesson/sms";


    @PostMapping("/SubmitSms")
    public void smsSubmit(@RequestBody SmsPojo sms) {
        try{
            smsService.send(sms);
        }
        catch(Exception e){

            webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ": Error sending the SMS: "+e.getMessage());
            throw e;
        }
        webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ": SMS has been sent!: "+sms.getPhonenumber());

    }

    @PostMapping("/smscallback")
    public void smsCallback(@RequestBody MultiValueMap<String, String> map) {
        smsService.receive(map);
        webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ": Twilio has made a callback request! Here are the contents: "+map.toString());
    }

    private String getTimeStamp() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
    }
}