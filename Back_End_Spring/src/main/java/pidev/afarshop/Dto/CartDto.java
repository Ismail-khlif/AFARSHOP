package pidev.afarshop.Dto;


import lombok.Builder;
import lombok.Data;
import pidev.afarshop.Entity.Cart;
import pidev.afarshop.Entity.Order1;
import pidev.afarshop.Entity.User;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class CartDto {

    private Long cartId;

    private Long productQuantity;

    User user;

    Order1 order;

    private List<ProductDto> products = new ArrayList<>();
    public CartDto fromEntity(Cart cart){
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
    public Cart fromEntity(CartDto cartdto){
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
