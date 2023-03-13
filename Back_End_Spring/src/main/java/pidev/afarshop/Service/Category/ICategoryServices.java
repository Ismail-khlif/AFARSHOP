package pidev.afarshop.Service.Category;

import pidev.afarshop.Entity.Category;

import java.util.List;

public interface ICategoryServices {
    void createCategory(Category category) ;
    void deleteCategory(Long  categoryId);
    void updateCategory(Category category);
    public boolean validateDictionary(String Dictionary);
    public List<Category> getAllCategorys();
    public Category getCategoryById(Long categoryId);
}
