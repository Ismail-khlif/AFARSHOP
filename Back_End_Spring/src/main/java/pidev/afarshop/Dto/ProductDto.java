package pidev.afarshop.Dto;

import lombok.Builder;
import lombok.Data;
import pidev.afarshop.Entity.*;
import java.util.Date;
import java.util.Set;

@Builder
@Data
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

    private Order1Dto order;

    UserDto user;

    Set<ProductCommentDto> productComments;

    private StoreDto store;

    Set<ProductLikeDto> productLikes;

    private CartDto cart;

    private ProductCategory category;

    public Product toEntity(ProductDto productDto) {
        if (productDto == null) {
            //TODO EXCEPTION ERRROR
            return null;
        }
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
                .order(productDto.getOrder())
                .user(productDto.getUser())
                .productComments(productDto.getProductComments())
                .store(productDto.getStore())
                .productLikes(productDto.getProductLikes())
                .cart(productDto.getCart())
                .category(productDto.getCategory())
                .build();
    }
    public ProductDto toDto(Product product){
        if(product==null){
            //TODO EXCEPTION ERRROR
            return null;
        }
        return  ProductDto.builder()
                .productId(product.getProductId())
                .reference(product.getReference())
                .productName(product.getProductName())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .images(product.getImages())
                .video(product.getVideo())
                .brand(product.getBrand())
                .price(product.getPrice())
                .dateOfProduct(product.getDateOfProduct())
                .rating(product.getRating())
                .discount(product.getDiscount())
                .yearsOfWarranty(product.getYearsOfWarranty())
                .facility(product.isFacility())
                .order(product.getOrder())
                .user(product.getUser())
                .productComments(product.getProductComments())
                .store(product.getStore())
                .productLikes(product.getProductLikes())
                .cart(product.getCart())
                .category(product.getCategory())
                .build();
    }
}
