package pidev.afarshop.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import javax.persistence.*;
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
    private String orderStatus;
    private Float amountBill;
    @OneToOne
    private Bill bill;
    @OneToMany(mappedBy = "order")
    private Set<Product> products;

    @OneToOne
    Cart cart ;

    @JsonIgnore
    @ManyToOne
    Delivery delivery;

    //BY oumaima 
    @ManyToOne
    private User user;

    



}