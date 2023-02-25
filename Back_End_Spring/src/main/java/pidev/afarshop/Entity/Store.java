package pidev.afarshop.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "Store")
public class Store  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="storeId")
    private Long storeId;
    private  String storeName ;
    private String storeLocation;
    private Integer contactInformation ;
    private String storeDescription ;
    private String storeEmailAddress ;
    /*olfa*/
}