package pidev.afarshop.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import java.util.List;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Data
@Table(name = "Store")
public class Store  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="storeId")
    private Long storeId;

    @NotBlank(message = "Store name should not be null")
    private  String storeName ;

    @NotBlank(message = "The address is required.")
    private String storeLocation;
    private Integer contactInformation ;

    @NotEmpty(message = "A store description is required.")
    @Size(min = 2, max = 200, message = "The length of the store description must be between 2 and 200 characters.")
    private String storeDescription ;

    @NotEmpty(message = "The email address is required.")
    @Email(message = "The email address is invalid.", flags = { Pattern.Flag.CASE_INSENSITIVE })
    private String storeEmailAddress ;



    @Lob
    @Column(name = "image", unique = false, nullable = false, length = 100000)
    private byte[] image;

    private String ImagePath;


    @JsonBackReference
    @ManyToOne
    private Category category ;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "store")
    private Set<Rating> ratings;

    @OneToMany
    @JsonIgnore
    private List<StoreLocations> storeLocations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "store")
    private Set<Product> products;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Set<Quiz> quiz;


}
    
