package pidev.afarshop.Dto;

import lombok.Builder;
import lombok.Data;
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

}
