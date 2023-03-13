package pidev.afarshop.Controller.Payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pidev.afarshop.Entity.SmsRequest;
import pidev.afarshop.Service.Payment.TwilioSmsSender;

@RestController
@RequestMapping("/api/sms")
public class SmsController {

    private final TwilioSmsSender smsSender;

    @Autowired
    public SmsController(TwilioSmsSender smsSender) {
        this.smsSender = smsSender;
    }

    @PostMapping
    public ResponseEntity<String> sendSms(@RequestBody SmsRequest smsRequest) {
        smsSender.sendSms(smsRequest);
        return ResponseEntity.ok("SMS message sent successfully.");
    }
}
