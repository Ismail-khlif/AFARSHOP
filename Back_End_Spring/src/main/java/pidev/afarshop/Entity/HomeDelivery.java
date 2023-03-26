package pidev.afarshop.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "Homedelivery")
@Builder
public class HomeDelivery  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="homeDeliveryId")
    private Long homeDeliveryId;
    @JsonFormat(pattern="dd/MM/yy")
    private Date deliveryDate;
    private String address ;
    private int postalCode;


    @OneToOne(mappedBy = "homeDelivery")
    @JsonIgnore
    private Delivery delivery ;
}
