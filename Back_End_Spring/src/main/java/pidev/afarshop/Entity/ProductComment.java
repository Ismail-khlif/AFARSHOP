package pidev.afarshop.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
@Builder
public class ProductComment implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ProductCommentId;

    String commentBody;

    Date commentedAt;


    @ManyToOne
    User user; // The user who wants to comment

    @JsonIgnore
    @ManyToOne
    Product product; // The Product to comment

}
