package pidev.afarshop.Entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "Order1")
public class Order1 implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="orderId")
    private Long orderId;

    @ManyToOne
    private User user;

    @ManyToMany(mappedBy = "orders", fetch = FetchType.EAGER)
    private List<Product> products;


}