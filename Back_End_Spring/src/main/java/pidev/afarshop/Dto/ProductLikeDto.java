package pidev.afarshop.Dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class ProductLikeDto {

    Long productLikeId;

    LocalDate likedAt;

    Boolean isLiked ;

    UserDto user; // The user who clicked Like

    ProductDto product; // The post to like

}
