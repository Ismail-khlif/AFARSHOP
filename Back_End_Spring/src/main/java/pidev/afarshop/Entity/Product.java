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
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @ManyToMany
    @JsonIgnore
    private List<Order1> orders;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}