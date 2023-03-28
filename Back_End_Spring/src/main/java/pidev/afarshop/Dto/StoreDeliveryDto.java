package pidev.afarshop.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pidev.afarshop.Entity.Delivery;
import pidev.afarshop.Entity.StoreDelivery;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreDeliveryDto {

    private Long storeDeliveryId;

    private Date pickUpDate;

    private String store ;
    @JsonIgnore
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
