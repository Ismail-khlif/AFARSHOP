package pidev.afarshop.Dto;

import lombok.Builder;
import lombok.Data;
import pidev.afarshop.Entity.Delivery;
import pidev.afarshop.Entity.Product;
import pidev.afarshop.Entity.ProductLike;
import pidev.afarshop.Entity.User;

import java.time.LocalDate;

@Data
@Builder
public class ProductLikeDto {

    Long productLikeId;

    LocalDate likedAt;

    Boolean isLiked ;

    UserDto user; // The user who clicked Like

    ProductDto product; // The post to like

    public static ProductLike toEntity(ProductLikeDto productLikeDto) {
        if (productLikeDto == null) {
            //TODO EXCEPTION ERRROR
            return null;
        }
        User user1 =UserDto.toEntity(productLikeDto.getUser());
        Product product1 =ProductDto.toEntity(productLikeDto.getProduct());
        return ProductLike.builder()
                .productLikeId(productLikeDto.getProductLikeId())
                .likedAt(productLikeDto.getLikedAt())
                .isLiked(productLikeDto.getIsLiked())
                .user(user1)
                .product(product1)
                .build();
    }
    public static ProductLikeDto toDto(ProductLike productLike){
        if(productLike==null){
            //TODO EXCEPTION ERRROR
            return null;
        }
        UserDto userDto =UserDto.toDto(productLike.getUser());
        ProductDto productDto =ProductDto.toDto(productLike.getProduct());
        return  ProductLikeDto.builder()
                .productLikeId(productLike.getProductLikeId())
                .likedAt(productLike.getLikedAt())
                .isLiked(productLike.getIsLiked())
                .user(userDto)
                .product(productDto)
                .build();
    }

}
