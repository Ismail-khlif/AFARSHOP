package pidev.afarshop.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "Provider")
public class Provider  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="providerId")
    private Long providerId;
    private String providerName;
    private long providerPrice;
    @Temporal (TemporalType.DATE)
    private Date estimationDate;


    @JsonIgnore
    @OneToMany(mappedBy = "provider",cascade = CascadeType.ALL)
    List<Delivery> deliveries;
}