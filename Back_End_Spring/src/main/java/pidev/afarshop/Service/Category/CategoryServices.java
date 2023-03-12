package pidev.afarshop.Service.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.afarshop.Entity.Category;
import pidev.afarshop.Repository.CategoryRepository;
import pidev.afarshop.Repository.StoreRepository;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CategoryServices implements ICategoryServices{

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    StoreRepository storeRepository;

    @Override
    public void createCategory(Category category) {
        boolean x = validateDictionary(category.getDictionary());
        if (x == true)
        {
            categoryRepository.save(category);
        }
        else
        {
            System.out.println("insert a valid dictionary. EXEMPLE: word1-word2-word3 ");
        }
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        categoryRepository.delete(category);
    }

    @Override
    public List<Category> getAllCategorys() {
        List<Category> categorys = (List<Category>) categoryRepository.findAll();
        return categorys;

    }

    @Override
    public Category getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        return category;

    }

    @Override
    public void updateCategory(Category category) {
        categoryRepository.save(category);

    }

    @Override
    public boolean validateDictionary(String dictionary){

        String regex = "^([a-zA-Z0-9]+-[a-zA-Z0-9]*)+$";

        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(dictionary);

        if (m.matches()) {
            return true;
        } else {
            return false;
        }

    }

}
