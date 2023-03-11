package pidev.afarshop.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table( name = "DiscountCode")
public class DiscountCode implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long DiscountCodeId;
    private String code;
    private Integer discount;
    private Boolean Used;
}