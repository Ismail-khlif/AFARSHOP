package pidev.afarshop.Service.HappyHour;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.afarshop.Entity.HappyHour;

import java.time.DayOfWeek;
import java.time.LocalTime;


public interface IHappyHourService {
    public HappyHour scheduleHappyHour(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime);
    public void cancelHappyHour(Long happyId);
    public boolean isHappyHourNow();


}