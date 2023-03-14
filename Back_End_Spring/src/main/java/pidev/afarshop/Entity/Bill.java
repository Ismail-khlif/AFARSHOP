package pidev.afarshop.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class Bill implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;
    private int installmentsNb ;
    private double paymentAmount;
    @JsonFormat(pattern="dd/MM/yy")
    private Date billDate;


    @OneToOne(mappedBy = "bill")
    @JsonIgnore
    private Order1 order1;

    @OneToMany(mappedBy = "billPayment")
    private Set<Payment> payments;

}
