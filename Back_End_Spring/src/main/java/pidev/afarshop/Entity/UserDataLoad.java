package pidev.afarshop.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
@Builder
public class UserDataLoad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userDataLoad;


    String CategorieData;

    int nbrsRequet;

    @ManyToOne
    User user;
}
