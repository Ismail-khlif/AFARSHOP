package pidev.afarshop.Dto;

import lombok.Builder;
import lombok.Data;
import pidev.afarshop.Entity.Delivery;
import pidev.afarshop.Entity.Rating;

@Builder
@Data
public class RatingDto {

    private Long ratingId;

    private boolean isLiked;

    private UserDto user;

    private StoreDto store;

    public Rating toEntity(RatingDto ratingDto) {
        if (ratingDto == null) {
            //TODO EXCEPTION ERRROR
            return null;
        }
        return Rating.builder()
                .ratingId(ratingDto.getRatingId())
                .isLiked(ratingDto.isLiked())
                .user(ratingDto.getUser())
                .store(ratingDto.getStore())
                .build();
    }
    public RatingDto toDto(Rating rating){
        if(rating==null){
            //TODO EXCEPTION ERRROR
            return null;
        }
        return  RatingDto.builder()
                .ratingId(rating.getRatingId())
                .isLiked(rating.isLiked())
                .user(rating.getUser())
                .store(rating.getStore())
                .build();
    }
}
