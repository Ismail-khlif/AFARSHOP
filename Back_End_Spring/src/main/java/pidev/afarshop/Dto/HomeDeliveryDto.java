package pidev.afarshop.Dto;


import lombok.Builder;
import lombok.Data;
import pidev.afarshop.Entity.Delivery;
import pidev.afarshop.Entity.HomeDelivery;

import java.util.Date;
@Builder
@Data
public class HomeDeliveryDto {

    private Long homeDeliveryId;

    private Date deliveryDate;

    private String address ;

    private int postalCode;

    private DeliveryDto delivery ;
    public static HomeDelivery toEntity(HomeDeliveryDto homeDeliveryDto) {
        if (homeDeliveryDto == null) {
            //TODO EXCEPTION ERRROR
            return null;
        }
        return HomeDelivery.builder()
                .homeDeliveryId(homeDeliveryDto.getHomeDeliveryId())
                .deliveryDate(homeDeliveryDto.getDeliveryDate())
                .address(homeDeliveryDto.getAddress())
                .postalCode(homeDeliveryDto.getPostalCode())
                .build();
    }
    public static HomeDeliveryDto toDto(HomeDelivery homeDelivery){
        if(homeDelivery==null){
            //TODO EXCEPTION ERRROR
            return null;
        }
        return  HomeDeliveryDto.builder()
                .homeDeliveryId(homeDelivery.getHomeDeliveryId())
                .deliveryDate(homeDelivery.getDeliveryDate())
                .address(homeDelivery.getAddress())
                .postalCode(homeDelivery.getPostalCode())
                .build();
    }
}
