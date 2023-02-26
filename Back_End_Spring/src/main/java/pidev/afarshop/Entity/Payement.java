package pidev.afarshop.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "Payement")
public class Payement  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payementId;
    private boolean paid;
    @JsonFormat(pattern="dd/MM/yy")
    private Date paymentDate;
    private float installmentAmount;

    @ManyToOne
    private Bill billPayment;


}