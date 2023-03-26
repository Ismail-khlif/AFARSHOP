package pidev.afarshop.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Forbidden {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long Forbiddenid ;
    private String text ;


}
