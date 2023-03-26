package pidev.afarshop.Dto;


import lombok.Builder;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class ProviderDto {

    private Long providerId;

    private String providerName;

    private long providerPrice;

    private Date estimationDate;

    List<DeliveryDto> deliveries;
}
