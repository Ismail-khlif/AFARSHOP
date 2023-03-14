package pidev.afarshop.Controller.HappyHourController;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidev.afarshop.Entity.HappyHour;
import pidev.afarshop.Service.HappyHour.HappyHourService;

import java.time.DayOfWeek;
import java.time.LocalTime;

@RestController
@AllArgsConstructor
@RequestMapping("/api/happyHour")
public class HappyHourController {
    @Autowired
    private HappyHourService happyHourService;

    @PostMapping("/schedule")
    public ResponseEntity<HappyHour> scheduleHappyHour(@RequestParam("dayOfWeek") DayOfWeek dayOfWeek,
                                                       @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
                                                       @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime) {
        HappyHour happyHour = happyHourService.scheduleHappyHour(dayOfWeek, startTime, endTime);
        return ResponseEntity.ok(happyHour);
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<?> cancelHappyHour(@PathVariable Long id) {
        happyHourService.cancelHappyHour(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/is-happy-hour-now")
    public ResponseEntity<?> isHappyHourNow() {
        boolean isHappyHour = happyHourService.isHappyHourNow();

        return new ResponseEntity<>(isHappyHour, HttpStatus.OK);
    }
}
