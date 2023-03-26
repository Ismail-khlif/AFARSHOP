package pidev.afarshop.Entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Data
@Table(name = "HappyHour")
@Builder
public class HappyHour implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="happyId")
    private Long happyId;

    private LocalTime startTime;
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

}
