package pidev.afarshop.Dto;

import lombok.Builder;
import lombok.Data;
import pidev.afarshop.Entity.Delivery;
import pidev.afarshop.Entity.Rating;
import pidev.afarshop.Entity.Store;
import pidev.afarshop.Entity.User;

@Builder
@Data
public class RatingDto {

    private Long ratingId;

    private boolean isLiked;

    private UserDto user;

    private StoreDto store;

    public static Rating toEntity(RatingDto ratingDto) {
        if (ratingDto == null) {
            //TODO EXCEPTION ERRROR
            return null;
        }
        User user1= UserDto.toEntity(ratingDto.getUser());
        Store store1 = StoreDto.toEntity(ratingDto.getStore());
        return Rating.builder()
                .ratingId(ratingDto.getRatingId())
                .isLiked(ratingDto.isLiked())
                .user(user1)
                .store(store1)
                .build();
    }
    public static RatingDto toDto(Rating rating){
        if(rating==null){
            //TODO EXCEPTION ERRROR
            return null;
        }
        UserDto userDto =UserDto.toDto(rating.getUser());
        StoreDto storeDto = StoreDto.toDto(rating.getStore());
        return  RatingDto.builder()
                .ratingId(rating.getRatingId())
                .isLiked(rating.isLiked())
                .user(userDto)
                .store(storeDto)
                .build();
    }
}
