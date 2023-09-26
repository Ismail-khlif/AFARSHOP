package pidev.afarshop.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name="images_modele")
public class ImageSModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    private String name ;
    private String type ;
    @Column(length = 50000000)
    private byte[] picByte;
    public ImageSModel(String originalFilename, String contentType, byte[] bytes) {
        this.name=originalFilename;
        this.type=contentType;
        this.picByte=bytes;
    }


}
