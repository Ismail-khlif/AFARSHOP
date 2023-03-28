package pidev.afarshop.Dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pidev.afarshop.Entity.Cart;
import pidev.afarshop.Entity.Order1;
import pidev.afarshop.Entity.User;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    private Long cartId;

    private Long productQuantity;
    @JsonIgnore
    User user;
    @JsonIgnore
    Order1 order;

    private List<ProductDto> products = new ArrayList<>();
    public static CartDto toDto(Cart cart){
        if(cart==null){
            //TODO ERROR EXCEPTION
            return null;
        }
        return CartDto.builder()
                .cartId(cart.getCartId())
                .order(cart.getOrder())
                .productQuantity(cart.getProductQuantity())
                .user(cart.getUser())
                .build();
    }
    public static Cart toEntity(CartDto cartdto){
        if(cartdto==null){
            //TODO ERROR EXCEPTION
            return null;
        }
        return Cart.builder()
                .cartId(cartdto.getCartId())
                .order(cartdto.getOrder())
                .productQuantity(cartdto.getProductQuantity())
                .user(cartdto.getUser())
                .build();
    }
}
