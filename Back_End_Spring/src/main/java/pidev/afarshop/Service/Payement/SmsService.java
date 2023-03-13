package pidev.afarshop.Service.Payement;

import com.twilio.exception.TwilioException;
import com.twilio.http.TwilioRestClient;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.twilio.rest.api.v2010.account.Message;

@Service
public class SmsService {

    private final TwilioRestClient twilioRestClient;
    private final PhoneNumber twilioPhoneNumber;

    @Autowired
    public SmsService(TwilioRestClient twilioRestClient, PhoneNumber twilioPhoneNumber) {
        this.twilioRestClient = twilioRestClient;
        this.twilioPhoneNumber = twilioPhoneNumber;
    }

    public void sendSms(String to, String message) throws TwilioException {
        Message.creator(new PhoneNumber(to), twilioPhoneNumber, message).create(twilioRestClient);
    }
}
