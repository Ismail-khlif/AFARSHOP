package pidev.afarshop.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

import java.time.LocalDate;
import java.util.Date;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString


public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    private boolean paid;
    @JsonFormat(pattern="dd/MM/yy")
    private LocalDate paymentDate;
    private float installmentAmount;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;


    @ManyToOne
    private Bill billPayment;



}