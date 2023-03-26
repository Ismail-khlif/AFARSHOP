package pidev.afarshop.Dto;


import lombok.Builder;
import lombok.Data;
import java.util.Date;

@Builder
@Data
public class ProductCommentDto {

    Long ProductCommentId;

    String commentBody;

    Date commentedAt;

    UserDto user; // The user who wants to comment

    ProductDto product; // The Product to comment

}
