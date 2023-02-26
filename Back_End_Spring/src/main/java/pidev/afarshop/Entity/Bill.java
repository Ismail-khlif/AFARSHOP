package pidev.afarshop.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
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
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private int installmentsNb ;
    private float paymentAmount;

    @OneToOne(mappedBy = "bill")
    private Order order;

    @OneToMany(mappedBy = "billPayment")
    private Set<Payement> payments;

}
