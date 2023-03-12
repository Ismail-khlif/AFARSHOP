package pidev.afarshop.Service.Order1;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import pidev.afarshop.Entity.*;


@Component
@Service
public class SmsService {

    //here
    private final String ACCOUNT_SID ="ACf8bed4c3284e859bb4b8f2a834e9f219";

    private final String AUTH_TOKEN = "d199d3c626b9fdc7561c267becce9ed1";

    private final String FROM_NUMBER = "+15746867677";

    public void send(SmsPojo sms) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(sms.getPhonenumber()), new PhoneNumber(FROM_NUMBER), sms.getMessage())
                .create();
        System.out.println("here is my id:"+message.getSid());// Unique resource ID created to manage this transaction

    }

    public void receive(MultiValueMap<String, String> smscallback) {
    }

}