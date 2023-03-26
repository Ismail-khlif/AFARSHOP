package pidev.afarshop.Entity;

import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.twilio.type.PhoneNumber;

@Configuration
@Data
@Builder
public class TwilioConfig {

    @Value("AC20887c66c515d0f7f341cb39c5c99ff7")
    private String accountSid;

    @Value("a269039bc1779b0b2dc59260efb2c1b4")
    private String authToken;

    @Value("+15073846037")
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
