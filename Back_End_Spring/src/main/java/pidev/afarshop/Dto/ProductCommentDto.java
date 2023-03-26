package pidev.afarshop.Dto;


import lombok.Builder;
import lombok.Data;
import pidev.afarshop.Entity.Delivery;
import pidev.afarshop.Entity.ProductComment;

import java.util.Date;

@Builder
@Data
public class ProductCommentDto {

    Long ProductCommentId;

    String commentBody;

    Date commentedAt;

    UserDto user; // The user who wants to comment

    ProductDto product; // The Product to comment

    public ProductComment toEntity(ProductCommentDto productCommentDto) {
        if (productCommentDto == null) {
            //TODO EXCEPTION ERRROR
            return null;
        }
        return ProductComment.builder()
                .ProductCommentId(productCommentDto.getProductCommentId())
                .commentBody(productCommentDto.getCommentBody())
                .commentedAt(productCommentDto.getCommentedAt())
                .user(productCommentDto.getUser())
                .product(productCommentDto.getProduct())
                .build();
    }
    public ProductCommentDto toDto(ProductComment productComment){
        if(productComment==null){
            //TODO EXCEPTION ERRROR
            return null;
        }
        return  ProductCommentDto.builder()
                .ProductCommentId(productComment.getProductCommentId())
                .commentBody(productComment.getCommentBody())
                .commentedAt(productComment.getCommentedAt())
                .user(productComment.getUser())
                .product(productComment.getProduct())
                .build();
    }
}
