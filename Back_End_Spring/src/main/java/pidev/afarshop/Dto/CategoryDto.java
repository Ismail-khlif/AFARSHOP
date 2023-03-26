package pidev.afarshop.Dto;

import lombok.Builder;
import lombok.Data;
import pidev.afarshop.Entity.Category;

import java.util.Set;

@Data
@Builder
public class CategoryDto {

    Long categoryId;

    String name;

    String dictionary;

    private Set<StoreDto> stores;
    public Category fromEntity(CategoryDto categoryDto){
        if (categoryDto == null){
            //TODO ERROR EXCEPTION
            return null;
        }
        return Category.builder()
                .categoryId(categoryDto.getCategoryId())
                .name(categoryDto.getName())
                .dictionary(categoryDto.getDictionary())
                .stores(categoryDto.getStores())
                .build();
    }
    public CategoryDto toEntity(Category category){
        if(category==null){
            //TODO ERROR EXCEPTION
            return null;
        }
        return CategoryDto.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .dictionary(category.getDictionary())
                .stores(category.getStores())
                .build();
    }
}
