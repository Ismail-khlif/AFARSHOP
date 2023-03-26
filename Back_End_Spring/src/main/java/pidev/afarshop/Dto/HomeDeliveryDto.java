package pidev.afarshop.Dto;


import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Builder
@Data
public class HomeDeliveryDto {

    private Long homeDeliveryId;

    private Date deliveryDate;

    private String address ;

    private int postalCode;

    private DeliveryDto delivery ;
}
