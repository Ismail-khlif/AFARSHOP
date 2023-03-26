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
}
