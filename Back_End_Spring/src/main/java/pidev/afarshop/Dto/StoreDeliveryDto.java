package pidev.afarshop.Dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class StoreDeliveryDto {

    private Long storeDeliveryId;

    private Date pickUpDate;

    private String store ;

    private DeliveryDto delivery ;

}
