package pidev.afarshop.Dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RatingDto {

    private Long ratingId;

    private boolean isLiked;

    private UserDto user;

    private StoreDto store;
}
