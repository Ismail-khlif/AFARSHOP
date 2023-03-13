package pidev.afarshop.Entity;

import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.twilio.type.PhoneNumber;

@Configuration
@Data
public class TwilioConfig {

    @Value("AC7047daeaee9ccc72a5ba56a49b897a82")
    private String accountSid;

    @Value("41e500433f8e3470fc057c1318a471b7")
    private String authToken;

    @Value("+15747014044")
    private String phoneNumber;

    public void initTwilio() {
        Twilio.init(accountSid, authToken);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Bean
    public TwilioRestClient twilioRestClient() {
        return new TwilioRestClient.Builder(accountSid, authToken).build();
    }

    @Bean
    public PhoneNumber twilioPhoneNumber() {
        return new PhoneNumber(phoneNumber);
    }
}
