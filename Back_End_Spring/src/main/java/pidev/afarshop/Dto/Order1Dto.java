package pidev.afarshop.Dto;

import lombok.Builder;
import lombok.Data;
import pidev.afarshop.Entity.Delivery;
import pidev.afarshop.Entity.Order1;

import java.util.Set;

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
        if (deliveryDto == null) {
            //TODO EXCEPTION ERRROR
            return null;
        }
        return Order1.builder()
                .orderId(order1Dto.getOrderId())
                .codePromo(order1Dto.getCodePromo())
                .orderStatus(order1Dto.getOrderStatus())
                .amountBill(order1Dto.getAmountBill())
                .bill(order1Dto.getBill())
                .products(order1Dto.getProducts())
                .delivery(order1Dto.getDelivery())
                .cart(order1Dto.getCart())
                .user(order1Dto.getUser())
                .build();
    }
    public static Order1Dto toDto(Order1 order1){
        if(delivery==null){
            //TODO EXCEPTION ERRROR
            return null;
        }
        return  Order1Dto.builder()
                .orderId(order1.getOrderId())
                .codePromo(order1.getCodePromo())
                .orderStatus(order1.getOrderStatus())
                .amountBill(order1.getAmountBill())
                .bill(order1.getBill())
                .products(order1.getProducts())
                .delivery(order1.getDelivery())
                .cart(order1.getCart())
                .user(order1.getUser())
                .build();
    }

}
