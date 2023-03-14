package pidev.afarshop.Service.HappyHour;

import com.twilio.Twilio;
import com.twilio.exception.TwilioException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import pidev.afarshop.Entity.HappyHour;
import pidev.afarshop.Repository.HappyHourRepository;
import pidev.afarshop.Service.Payement.SmsService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class HappyHourService implements IHappyHourService {

    private final String accountSid = "AC20887c66c515d0f7f341cb39c5c99ff7";
    private final String authToken = "a269039bc1779b0b2dc59260efb2c1b4";

    private final SmsService smsService;
    @Autowired
    private Environment environment;

    public void init() {
        String accountSid = environment.getProperty("accountSid");
        String authToken = environment.getProperty("authToken");

        try {
            Twilio.init(accountSid, authToken);
        } catch (TwilioException ex) {
            // log error
        }
    }
    @Autowired
    private HappyHourRepository happyHourRepository;

    public HappyHour scheduleHappyHour(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        HappyHour happyHour = new HappyHour();
        happyHour.setStartTime(startTime);
        happyHour.setEndTime(endTime);


        happyHourRepository.save(happyHour);

        return happyHour;
    }

    public void cancelHappyHour(Long happyId) {

        happyHourRepository.deleteById(happyId);
    }

    public boolean isHappyHourNow() {
        // Get the current time and day of the week
        LocalTime currentTime = LocalTime.now();
        DayOfWeek currentDayOfWeek = LocalDate.now().getDayOfWeek();

        // Check if there's a scheduled Happy Hour for the current day of the week
        Optional<HappyHour> scheduledHappyHour = happyHourRepository.findByDayOfWeek(currentDayOfWeek);
        if (scheduledHappyHour.isEmpty()) {
            return false;
        }

        // Check if the current time is within the scheduled Happy Hour
        HappyHour happyHour = scheduledHappyHour.get();
        if (currentTime.isAfter(happyHour.getStartTime()) && currentTime.isBefore(happyHour.getEndTime())) {
            return true;

        }
        String fromNumber = environment.getProperty("+15747014044");
        String message = "Your Happy Hour is NOW !";
        try {
            smsService.sendSms("+21650879536",message);
        } catch (TwilioException e) {
            // Gérer les erreurs d'envoi de SMS
        }

        return false;
    }
}
