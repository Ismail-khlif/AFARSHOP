package pidev.afarshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidev.afarshop.Entity.HappyHour;

import java.time.DayOfWeek;
import java.util.Optional;

@Repository
public interface HappyHourRepository extends JpaRepository<HappyHour,Long> {
    Optional<HappyHour> findByDayOfWeek(DayOfWeek currentDayOfWeek);
}
