package pidev.afarshop.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    private String reviewTitle;
    private String reviewText;
    private boolean verified;
    private float rating;
    @Temporal(TemporalType.DATE)
    private Date dateOfReview;
    @ManyToOne
    User user;

}
