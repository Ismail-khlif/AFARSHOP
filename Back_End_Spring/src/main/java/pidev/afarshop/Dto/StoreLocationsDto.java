package pidev.afarshop.Dto;

import lombok.Builder;
import lombok.Data;
import pidev.afarshop.Entity.Delivery;
import pidev.afarshop.Entity.StoreLocations;

@Builder
@Data
public class StoreLocationsDto {
    private Long id;

    private String address;

    private double latitude;

    private double longitude;
    public StoreLocations toEntity(StoreLocationsDto storeLocationsDto) {
        if (storeLocationsDto == null) {
            //TODO EXCEPTION ERRROR
            return null;
        }
        return StoreLocations.builder()
                .id(storeLocationsDto.getId())
                .address(storeLocationsDto.getAddress())
                .latitude(storeLocationsDto.getLatitude())
                .longitude(storeLocationsDto.getLongitude())
                .build();
    }
    public StoreLocationsDto toDto(StoreLocations storeLocations){
        if(storeLocations==null){
            //TODO EXCEPTION ERRROR
            return null;
        }
        return  StoreLocationsDto.builder()
                .id(storeLocations.getId())
                .address(storeLocations.getAddress())
                .latitude(storeLocations.getLatitude())
                .longitude(storeLocations.getLongitude())
                .build();
    }
}
