package pidev.afarshop.Dto;

import lombok.Builder;
import lombok.Data;
import pidev.afarshop.Entity.Delivery;
import pidev.afarshop.Entity.ProductLike;

import java.time.LocalDate;

@Data
@Builder
public class ProductLikeDto {

    Long productLikeId;

    LocalDate likedAt;

    Boolean isLiked ;

    UserDto user; // The user who clicked Like

    ProductDto product; // The post to like

    public ProductLike toEntity(ProductLikeDto productLikeDto) {
        if (productLikeDto == null) {
            //TODO EXCEPTION ERRROR
            return null;
        }
        return ProductLike.builder()
                .productLikeId(productLikeDto.getProductLikeId())
                .likedAt(productLikeDto.getLikedAt())
                .isLiked(productLikeDto.getIsLiked())
                .user(productLikeDto.getUser())
                .product(productLikeDto.getProduct())
                .build();
    }
    public ProductLikeDto toDto(ProductLike productLike){
        if(productLike==null){
            //TODO EXCEPTION ERRROR
            return null;
        }
        return  ProductLikeDto.builder()
                .productLikeId(productLike.getProductLikeId())
                .likedAt(productLike.getLikedAt())
                .isLiked(productLike.getIsLiked())
                .user(productLike.getUser())
                .product(productLike.getProduct())
                .build();
    }

}
