package pidev.afarshop.Entity;



import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;


import java.util.Set;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString


public class Order1 implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="orderId")
    private Long orderId;

    private String codePromo;
    @NotBlank(message = "amountBill required")
    private String orderStatus;
    @NotBlank(message = " amountBill required")
    private Float amountBill;
    
    @OneToOne
    @JsonIgnore
    private Bill bill;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "order")
    private Set<Product> products;



    @OneToOne
    @JsonIgnore
    private Delivery delivery;

    @OneToOne
    Cart cart ;

    //BY oumaima 
    //@ManyToOne
    //Delivery delivery;

    @ManyToOne
    @JsonIgnore
    private User user;

   
}
