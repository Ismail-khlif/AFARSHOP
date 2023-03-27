package pidev.afarshop.Dto;

import lombok.Builder;
import lombok.Data;
import pidev.afarshop.Entity.*;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Data
public class Order1Dto {

    private Long orderId;

    private String codePromo;

    private String orderStatus;

    private Float amountBill;

    private BillDto bill;

    private Set<ProductDto> products;

    private DeliveryDto delivery;

    CartDto cart ;

    private UserDto user;
    public static Order1 toEntity(Order1Dto order1Dto) {
        if (order1Dto == null) {
            //TODO EXCEPTION ERRROR
            return null;
        }
        Cart cart1 = CartDto.toEntity(order1Dto.getCart());
        Bill bill1 = BillDto.toEntity((order1Dto.getBill()));
        Set<Product> productSet = order1Dto.getProducts().stream()
                .map(ProductDto::toEntity)
                .collect(Collectors.toSet());
        Delivery delivery1 =DeliveryDto.toEntity(order1Dto.getDelivery());
        User user1 =UserDto.toEntity(order1Dto.getUser());
        return Order1.builder()
                .orderId(order1Dto.getOrderId())
                .codePromo(order1Dto.getCodePromo())
                .orderStatus(order1Dto.getOrderStatus())
                .amountBill(order1Dto.getAmountBill())
                .bill(bill1)
                .products(productSet)
                .delivery(delivery1)
                .cart(cart1)
                .user(user1)
                .build();
    }
    public static Order1Dto toDto(Order1 order1){
        if(order1==null){
            //TODO EXCEPTION ERRROR
            return null;
        }
        CartDto cartDto =CartDto.toDto(order1.getCart());
        BillDto bill1Dto =BillDto.toDto(order1.getBill());
        Set<ProductDto> productDtos = order1.getProducts().stream()
                .map(ProductDto::toDto)
                .collect(Collectors.toSet());
        DeliveryDto deliveryDto =DeliveryDto.toDto(order1.getDelivery());
        UserDto userDto=UserDto.toDto(order1.getUser());
        return  Order1Dto.builder()
                .orderId(order1.getOrderId())
                .codePromo(order1.getCodePromo())
                .orderStatus(order1.getOrderStatus())
                .amountBill(order1.getAmountBill())
                .bill(bill1Dto)
                .products(productDtos)
                .delivery(deliveryDto)
                .cart(cartDto)
                .user(userDto)
                .build();
    }

}
