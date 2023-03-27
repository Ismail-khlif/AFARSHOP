package pidev.afarshop.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import pidev.afarshop.Entity.Category;
import pidev.afarshop.Entity.Store;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class CategoryDto {

    Long categoryId;

    String name;

    String dictionary;
    @JsonIgnore
    private Set<StoreDto> stores;
    public Category toEntity(CategoryDto categoryDto){
        if (categoryDto == null){
            //TODO ERROR EXCEPTION
            return null;
        }
        Set<Store> stores = categoryDto.getStores().stream()
                .map(StoreDto::toEntity)
                .collect(Collectors.toSet());
        return Category.builder()
                .categoryId(categoryDto.getCategoryId())
                .name(categoryDto.getName())
                .dictionary(categoryDto.getDictionary())
                .stores(stores)
                .build();
    }
    public static CategoryDto toDto(Category category){
        if(category==null){
            //TODO ERROR EXCEPTION
            return null;
        }
        Set<StoreDto> storeDtos = category.getStores().stream()
                .map(StoreDto::toDto)
                .collect(Collectors.toSet());
        return CategoryDto.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .dictionary(category.getDictionary())
                .stores(storeDtos)
                .build();
    }
}
