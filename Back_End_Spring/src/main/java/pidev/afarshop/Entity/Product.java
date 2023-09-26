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
@Builder
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

    @ManyToMany(fetch= FetchType.EAGER ,cascade = CascadeType.ALL)
    @JoinTable(name="product_images",
            joinColumns = {
                    @JoinColumn(name="product_id")
            },inverseJoinColumns = {
            @JoinColumn(name = "image_id")
    }
    )
    private Set<ImageSModel> images;


    private String brand;

    private double price;
    @Temporal(TemporalType.DATE)
    private Date dateOfProduct;
    private float rating;
    private float discount;

    private int yearsOfWarranty;

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