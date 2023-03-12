package pidev.afarshop.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "Product")
public class Product  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String reference;
    private String productName;
    private String description;
    private Long quantity;
    @Lob
    private byte[] images;
    @Lob
    private byte[] video;
    private String brand;
    private float price;
    @Temporal(TemporalType.DATE)
    private Date dateOfProduct;
    private float rating;
    private float discount;
    private int yearsOfWarranty;
    private boolean facility;

    @ManyToOne
    private Order1 order;
    @ManyToOne
    User user;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    Set<ProductComment> productComments;
    
    @ManyToOne
    @JoinColumn(name = "storeId")
    private Store store;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    Set<ProductLike> productLikes;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private Cart cart;


    @Enumerated(EnumType.STRING)
    private ProductCategory category;


   
}