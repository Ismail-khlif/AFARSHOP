package pidev.afarshop.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pidev.afarshop.Entity.*;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long productId;

    private String reference;

    private String productName;

    private String description;

    private Long quantity;

    private byte[] images;

    private byte[] video;

    private String brand;

    private double price;

    private Date dateOfProduct;

    private float rating;

    private float discount;

    private int yearsOfWarranty;

    private boolean facility;
    @JsonIgnore
    private Order1Dto order;
    @JsonIgnore
    UserDto user;
    @JsonIgnore
    Set<ProductCommentDto> productComments;
    @JsonIgnore
    private StoreDto store;
    @JsonIgnore
    Set<ProductLikeDto> productLikes;
    @JsonIgnore
    private CartDto cart;
    @JsonIgnore
    private ProductCategory category;


    public static Product toEntity(ProductDto productDto) {
        if (productDto == null) {
            //TODO EXCEPTION ERRROR
            return null;
        }
        Order1 order1 =Order1Dto.toEntity(productDto.getOrder());
        User user1 =UserDto.toEntity(productDto.getUser());
        Store store1= StoreDto.toEntity(productDto.getStore());
        Cart cart1=CartDto.toEntity(productDto.getCart());
        Set<ProductComment> productCommentSet = productDto.getProductComments().stream()
                .map(ProductCommentDto::toEntity)
                .collect(Collectors.toSet());
        Set<ProductLike> productLikeSet = productDto.getProductLikes().stream()
                .map(ProductLikeDto::toEntity)
                .collect(Collectors.toSet());
        return Product.builder()
                .productId(productDto.getProductId())
                .reference(productDto.getReference())
                .productName(productDto.getProductName())
                .description(productDto.getDescription())
                .quantity(productDto.getQuantity())
                .images(productDto.getImages())
                .video(productDto.getVideo())
                .brand(productDto.getBrand())
                .price(productDto.getPrice())
                .dateOfProduct(productDto.getDateOfProduct())
                .rating(productDto.getRating())
                .discount(productDto.getDiscount())
                .yearsOfWarranty(productDto.getYearsOfWarranty())
                .facility(productDto.isFacility())
                .order(order1)
                .user(user1)
                .productComments(productCommentSet)
                .store(store1)
                .productLikes(productLikeSet)
                .cart(cart1)
                .category(productDto.getCategory())
                .build();
    }
    public static ProductDto toDto(Product product){
        if(product==null){
            //TODO EXCEPTION ERRROR
            return null;
        }
        Set<ProductCommentDto> productCommentDtos = product.getProductComments().stream()
                .map(ProductCommentDto::toDto)
                .collect(Collectors.toSet());
        Set<ProductLikeDto> productLikeDtos = product.getProductLikes().stream()
                .map(ProductLikeDto::toDto)
                .collect(Collectors.toSet());
        Order1Dto order1Dto=Order1Dto.toDto(product.getOrder());
        UserDto userDto=UserDto.toDto(product.getUser());
        StoreDto storeDto = StoreDto.toDto(product.getStore());
        CartDto cartDto =CartDto.toDto(product.getCart());
        return  ProductDto.builder()
                .productId(product.getProductId())
                .reference(product.getReference())
               // .productName(product.getProductName())
                //.description(product.getDescription())
               // .quantity(product.getQuantity())
               // .images(product.getImages())
                //.video(product.getVideo())
                //.brand(product.getBrand())
                //.price(product.getPrice())
                //.dateOfProduct(product.getDateOfProduct())
                //.rating(product.getRating())
                //.discount(product.getDiscount())
                //.yearsOfWarranty(product.getYearsOfWarranty())
                .facility(product.isFacility())
                //.order(order1Dto)
                //.user(userDto)
                //.productComments(productCommentDtos)
                .store(storeDto)
                //.productLikes(productLikeDtos)
                //.cart(cartDto)
                //.category(product.getCategory())
                .build();
    }
}
