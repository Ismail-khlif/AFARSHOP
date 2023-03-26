package pidev.afarshop.Dto;

import lombok.Builder;
import lombok.Data;
import pidev.afarshop.Entity.Delivery;
import pidev.afarshop.Entity.StoreDelivery;

import java.util.Date;

@Builder
@Data
public class StoreDeliveryDto {

    private Long storeDeliveryId;

    private Date pickUpDate;

    private String store ;

    private DeliveryDto delivery ;

    public StoreDelivery toEntity(StoreDeliveryDto storeDeliveryDto) {
        if (storeDeliveryDto == null) {
            //TODO EXCEPTION ERRROR
            return null;
        }
        return StoreDelivery.builder()
                .storeDeliveryId(storeDeliveryDto.getStoreDeliveryId())
                .pickUpDate(storeDeliveryDto.getPickUpDate())
                .store(storeDeliveryDto.getStore())
                .delivery(storeDeliveryDto.getDelivery())
                .build();
    }
    public StoreDeliveryDto toDto(StoreDelivery storeDelivery){
        if(storeDelivery==null){
            //TODO EXCEPTION ERRROR
            return null;
        }
        return  StoreDeliveryDto.builder()
                .storeDeliveryId(storeDelivery.getStoreDeliveryId())
                .pickUpDate(storeDelivery.getPickUpDate())
                .store(storeDelivery.getStore())
                .delivery(storeDelivery.getDelivery())
                .build();
    }

}
