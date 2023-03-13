package pidev.afarshop.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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
@Table(name = "Storedelivery")
public class StoreDelivery  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="storeDeliveryId")
    private Long storeDeliveryId;
    @JsonFormat(pattern="dd/MM/yy")
    private Date pickUpDate;
    private String store ;

    @OneToOne(mappedBy = "storeDelivery")
    @JsonIgnore
    private Delivery delivery ;

}
