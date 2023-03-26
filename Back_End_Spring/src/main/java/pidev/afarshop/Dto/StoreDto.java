package pidev.afarshop.Dto;

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

    private CategoryDto category ;

    private Set<RatingDto> ratings;

    private List<StoreLocationsDto> storeLocations;

    private Set<ProductDto> products;

    Set<QuizDto> quiz;
}
