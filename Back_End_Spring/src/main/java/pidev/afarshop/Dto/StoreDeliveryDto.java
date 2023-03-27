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

    public static StoreDelivery toEntity(StoreDeliveryDto storeDeliveryDto) {
        if (storeDeliveryDto == null) {
            //TODO EXCEPTION ERRROR
            return null;
        }
        Delivery delivery1=DeliveryDto.toEntity(storeDeliveryDto.getDelivery());

        return StoreDelivery.builder()
                .storeDeliveryId(storeDeliveryDto.getStoreDeliveryId())
                .pickUpDate(storeDeliveryDto.getPickUpDate())
                .store(storeDeliveryDto.getStore())
                .delivery(delivery1)
                .build();
    }
    public static StoreDeliveryDto toDto(StoreDelivery storeDelivery){
        if(storeDelivery==null){
            //TODO EXCEPTION ERRROR
            return null;
        }
        DeliveryDto deliveryDto =DeliveryDto.toDto(storeDelivery.getDelivery());
        return  StoreDeliveryDto.builder()
                .storeDeliveryId(storeDelivery.getStoreDeliveryId())
                .pickUpDate(storeDelivery.getPickUpDate())
                .store(storeDelivery.getStore())
                .delivery(deliveryDto)
                .build();
    }

}
