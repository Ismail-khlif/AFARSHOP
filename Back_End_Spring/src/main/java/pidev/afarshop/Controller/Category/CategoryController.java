package pidev.afarshop.Controller.Category;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.afarshop.Entity.Category;
import pidev.afarshop.Service.Category.ICategoryServices;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategoryController {
    @Autowired
    ICategoryServices categoryServices;

    @PostMapping(path = "/create",consumes = {"application/json"})
    @ResponseBody
    public void createCategory(@RequestBody Category category) {
        categoryServices.createCategory(category);
    }

    @GetMapping("/getAllCategorys")
    @ResponseBody
    public List<Category> getAllCategorys() {

        List<Category> categorys = categoryServices.getAllCategorys();
        return categorys;

    }

    @GetMapping("/getCategoryById/{id}")
    @ResponseBody
    public Category getCategoryById(@PathVariable("id") Long categoryId) {

        Category category = categoryServices.getCategoryById(categoryId);
        return category;

    }

    @DeleteMapping("/delete/{id}")
    public void deleteCar(@PathVariable("id") Long categoryId) {

        categoryServices.deleteCategory(categoryId);
        ;
    }

    @PutMapping("/update")
    public void updateCategory(@RequestBody Category category) {
        categoryServices.updateCategory(category);
    }
}
