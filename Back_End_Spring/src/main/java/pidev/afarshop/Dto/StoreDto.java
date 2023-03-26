package pidev.afarshop.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import pidev.afarshop.Entity.*;

import java.util.List;
import java.util.Set;

@Builder
@Data
public class StoreDto {

    private Long storeId;

    private  String storeName ;

    private String storeLocation;

    private Integer contactInformation ;

    private String storeDescription ;

    private String storeEmailAddress ;

    private byte[] image;

    private String ImagePath;

    private double Score;

    private Evaluation evaluation;

    private Integer nbLikes;

    private Integer nbDislikes;
    @JsonIgnore
    private CategoryDto category ;

    private Set<RatingDto> ratings;

    private List<StoreLocationsDto> storeLocations;

    private Set<ProductDto> products;

    Set<QuizDto> quiz;

    public static Store toEntity(StoreDto storeDto) {
        if (storeDto == null) {
            //TODO EXCEPTION ERRROR
            return null;
        }
        return Store.builder()
                .storeId(storeDto.getStoreId())
                .storeName(storeDto.getStoreName())
                .storeLocation(storeDto.getStoreLocation())
                .contactInformation(storeDto.getContactInformation())
                .storeDescription(storeDto.getStoreDescription())
                .storeEmailAddress(storeDto.getStoreEmailAddress())
                .image(storeDto.getImage())
                .ImagePath(storeDto.getImagePath())
                .Score(storeDto.getScore())
                .evaluation(storeDto.getEvaluation())
                .nbLikes(storeDto.getNbLikes())
                .nbDislikes(storeDto.getNbDislikes())
                .category(storeDto.getCategory())
                .ratings(storeDto.getRatings())
                .storeLocations(storeDto.getStoreLocations())
                .products(storeDto.getProducts())
                .quiz(storeDto.getQuiz())
                .build();
    }
    public static StoreDto toDto(Store store){
        if(store==null){
            //TODO EXCEPTION ERRROR
            return null;
        }
        return  StoreDto.builder()
                .storeId(store.getStoreId())
                .storeName(store.getStoreName())
                .storeLocation(store.getStoreLocation())
                .contactInformation(store.getContactInformation())
                .storeDescription(store.getStoreDescription())
                .storeEmailAddress(store.getStoreEmailAddress())
                .image(store.getImage())
                .ImagePath(store.getImagePath())
                .Score(store.getScore())
                .evaluation(store.getEvaluation())
                .nbLikes(store.getNbLikes())
                .nbDislikes(store.getNbDislikes())
                .category(store.getCategory())
                .ratings(store.getRatings())
                .storeLocations(store.getStoreLocations())
                .products(store.getProducts())
                .quiz(store.getQuiz())
                .build();
    }
}
