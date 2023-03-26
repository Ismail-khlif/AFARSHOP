package pidev.afarshop.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
@Builder
public class CommentD {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idCom ;
    private String tenor ;
    private Date date ;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "commentd")
    @JsonIgnore
    private List<ReactionC> reactionc ;

    @ManyToOne
    @JsonIgnore
    private Publication publication ;

    @ManyToOne
    @JsonIgnore
    private User user ;

}
