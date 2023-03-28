package pidev.afarshop.Entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Reaction {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id ;
    @Enumerated(EnumType.STRING)
    private ReactionValue value ;



    @ManyToOne
    private Publication publication ;

    @JsonIgnore
    @OneToMany(mappedBy ="user" )
    private List<CommentD> commentDs  ;

    @ManyToOne
    @JsonIgnore
    private User user ;



}
