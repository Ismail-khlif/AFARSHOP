package pidev.afarshop.Entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long categoryId;
    String name;
    String dictionary;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
    private Set<Store> stores;
}

