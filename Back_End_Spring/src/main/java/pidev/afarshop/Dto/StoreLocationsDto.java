package pidev.afarshop.Dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StoreLocationsDto {
    private Long id;

    private String address;

    private double latitude;

    private double longitude;
}
