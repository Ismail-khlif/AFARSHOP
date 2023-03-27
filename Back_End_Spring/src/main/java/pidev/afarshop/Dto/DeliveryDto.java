package pidev.afarshop.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private HomeDeliveryDto homeDelivery ;

    private StoreDeliveryDto storeDelivery ;

    private Order1Dto order;

    ProviderDto provider;

    public static DeliveryDto toDto(Delivery delivery){
        if(delivery==null){
            //TODO EXCEPTION ERRROR
            return null;
        }
        HomeDeliveryDto homeDeliveryDto = HomeDeliveryDto.toDto(delivery.getHomeDelivery());
        StoreDeliveryDto storeDeliveryDto = StoreDeliveryDto.toDto(delivery.getStoreDelivery());
        Order1Dto order1Dto = Order1Dto.toDto(delivery.getOrder());
        ProviderDto providerDto = ProviderDto.toDto(delivery.getProvider());
        return  DeliveryDto.builder()
                .DeliveryId(delivery.getDeliveryId())
                .creationDate(delivery.getCreationDate())
                .status(delivery.getStatus())
                .deliveryMode(delivery.getDeliveryMode())
                .firstName(delivery.getFirstName())
                .lastName(delivery.getLastName())
                .numTel(delivery.getNumTel())
                .homeDelivery(homeDeliveryDto)
                .storeDelivery(storeDeliveryDto)
                .order(order1Dto)
                .provider(providerDto)
                .build();
    }
    public static Delivery toEntity(DeliveryDto deliveryDto){
        if(deliveryDto==null){
            //TODO EXCEPTION ERRROR
            return null;
        }
        HomeDelivery homeDelivery = HomeDeliveryDto.toEntity(deliveryDto.getHomeDelivery());
        StoreDelivery storeDelivery = StoreDeliveryDto.toEntity(deliveryDto.getStoreDelivery());
        Order1 order1 = Order1Dto.toEntity(deliveryDto.getOrder());
        Provider provider = ProviderDto.toEntity(deliveryDto.getProvider());
        return  Delivery.builder()
                .DeliveryId(deliveryDto.getDeliveryId())
                .creationDate(deliveryDto.getCreationDate())
                .status(deliveryDto.getStatus())
                .deliveryMode(deliveryDto.getDeliveryMode())
                .firstName(deliveryDto.getFirstName())
                .lastName(deliveryDto.getLastName())
                .numTel(deliveryDto.getNumTel())
                .homeDelivery(homeDelivery)
                .storeDelivery(storeDelivery)
                .order(order1)
                .provider(provider)
                .build();
    }
}
