package pidev.afarshop.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Delivery implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="delivery_id")
    private Long DeliveryId;
    @JsonFormat(pattern="dd/MM/yy")
    private Date creationDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private DeliveryMode deliveryMode;
    private String firstName ;
    private String lastName ;
    private Long numTel;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "home_deliveryid", referencedColumnName = "homeDeliveryId")
    private HomeDelivery homeDelivery ;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "store_deliveryid", referencedColumnName = "storeDeliveryId")
    private StoreDelivery storeDelivery ;

}