package pidev.afarshop.Dto;


import lombok.Builder;
import lombok.Data;
import pidev.afarshop.Entity.Delivery;
import pidev.afarshop.Entity.Provider;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ProviderDto {

    private Long providerId;

    private String providerName;

    private long providerPrice;

    private Date estimationDate;

    List<DeliveryDto> deliveries;
    public static Provider toEntity(ProviderDto providerDto) {
        if (providerDto == null) {
            //TODO EXCEPTION ERRROR
            return null;
        }
        List<Delivery> deliveryList =providerDto.getDeliveries().stream()
                .map(DeliveryDto::toEntity)
                .collect(Collectors.toList());
        return Provider.builder()
                .providerId(providerDto.getProviderId())
                .providerName(providerDto.getProviderName())
                .providerPrice(providerDto.getProviderPrice())
                .estimationDate(providerDto.getEstimationDate())
                .deliveries(deliveryList)
                .build();
    }
    public static ProviderDto toDto(Provider provider){
        if(provider==null){
            //TODO EXCEPTION ERRROR
            return null;
        }
        List<DeliveryDto> deliveryDtosList =provider.getDeliveries().stream()
                .map(DeliveryDto::toDto)
                .collect(Collectors.toList());
        return  ProviderDto.builder()
                .providerId(provider.getProviderId())
                .providerName(provider.getProviderName())
                .providerPrice(provider.getProviderPrice())
                .estimationDate(provider.getEstimationDate())
                .deliveries(deliveryDtosList)
                .build();
    }
}
