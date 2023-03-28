package pidev.afarshop.Dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pidev.afarshop.Entity.Delivery;
import pidev.afarshop.Entity.Product;
import pidev.afarshop.Entity.ProductComment;
import pidev.afarshop.Entity.User;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCommentDto {

    Long ProductCommentId;

    String commentBody;

    Date commentedAt;
    @JsonIgnore
    UserDto user; // The user who wants to comment
    @JsonIgnore
    ProductDto product; // The Product to comment

    public static ProductComment toEntity(ProductCommentDto productCommentDto) {
        if (productCommentDto == null) {
            //TODO EXCEPTION ERRROR
            return null;
        }
        User user1 =UserDto.toEntity(productCommentDto.getUser());
        Product product1 =ProductDto.toEntity(productCommentDto.getProduct());
        return ProductComment.builder()
                .ProductCommentId(productCommentDto.getProductCommentId())
                .commentBody(productCommentDto.getCommentBody())
                .commentedAt(productCommentDto.getCommentedAt())
                .user(user1)
                .product(product1)
                .build();
    }
    public static ProductCommentDto toDto(ProductComment productComment){
        if(productComment==null){
            //TODO EXCEPTION ERRROR
            return null;
        }
        UserDto userDto =UserDto.toDto(productComment.getUser());
        ProductDto productDto =ProductDto.toDto(productComment.getProduct());
        return  ProductCommentDto.builder()
                .ProductCommentId(productComment.getProductCommentId())
                .commentBody(productComment.getCommentBody())
                .commentedAt(productComment.getCommentedAt())
                //.user(userDto)
                .product(productDto)
                .build();
    }
}
