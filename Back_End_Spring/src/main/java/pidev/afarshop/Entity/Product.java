package pidev.afarshop.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "reference required")
    private String reference;
    @NotBlank(message = "productName required")
    private String productName;
    @NotBlank(message = "description required")
    private String description;
    @NotBlank(message = "quantity required")
    private Long quantity;

    @Lob
    private byte[] images;
    @Lob
    private byte[] video;
    @NotBlank(message = "brand required")
    private String brand;
    @NotBlank(message = "price required")
    private double price;
    @Temporal(TemporalType.DATE)
    private Date dateOfProduct;
    private float rating;
    private float discount;
    @NotBlank(message = "yearsOfWarranty required")
    private int yearsOfWarranty;
    @NotBlank(message = "facility required")
    private boolean facility;
    @JsonIgnore
    @ManyToOne

    private Order1 order;

    @ManyToOne
    User user;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    Set<ProductComment> productComments;
    
    @ManyToOne
    @JsonIgnore
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