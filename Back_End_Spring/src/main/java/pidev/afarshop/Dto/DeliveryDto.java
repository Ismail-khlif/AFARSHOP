package pidev.afarshop.Dto;

import lombok.Builder;
import lombok.Data;
import pidev.afarshop.Entity.*;

import java.util.Date;

@Data
@Builder
public class DeliveryDto {

    private Long DeliveryId;

    private Date creationDate;

    private Status status;

    private DeliveryMode deliveryMode;

    private String firstName ;

    private String lastName ;

    private Long numTel;

    private HomeDeliveryDto homeDelivery ;

    private StoreDeliveryDto storeDelivery ;

    private Order1Dto order;

    ProviderDto provider;

    public DeliveryDto toEntity(Delivery delivery){
        if(delivery==null){
            //TODO EXCEPTION ERRROR
            return null;
        }
        return  DeliveryDto.builder()
                .DeliveryId(delivery.getDeliveryId())
                .creationDate(delivery.getCreationDate())
                .status(delivery.getStatus())
                .deliveryMode(delivery.getDeliveryMode())
                .firstName(delivery.getFirstName())
                .lastName(delivery.getLastName())
                .numTel(delivery.getNumTel())
                .homeDelivery(delivery.getHomeDelivery())
                .storeDelivery(delivery.getStoreDelivery())
                .order(delivery.getOrder())
                .provider(delivery.getProvider())
                .build();
    }
    public Delivery toEntity(DeliveryDto deliveryDto){
        if(deliveryDto==null){
            //TODO EXCEPTION ERRROR
            return null;
        }
        return  Delivery.builder()
                .DeliveryId(deliveryDto.getDeliveryId())
                .creationDate(deliveryDto.getCreationDate())
                .status(deliveryDto.getStatus())
                .deliveryMode(deliveryDto.getDeliveryMode())
                .firstName(deliveryDto.getFirstName())
                .lastName(deliveryDto.getLastName())
                .numTel(deliveryDto.getNumTel())
                .homeDelivery(deliveryDto.getHomeDelivery())
                .storeDelivery(deliveryDto.getStoreDelivery())
                .order(deliveryDto.getOrder())
                .provider(deliveryDto.getProvider())
                .build();
    }
}
