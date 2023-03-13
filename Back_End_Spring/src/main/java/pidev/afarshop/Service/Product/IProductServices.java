package pidev.afarshop.Service.Product;

import org.springframework.web.bind.annotation.RequestBody;
import pidev.afarshop.Entity.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

public interface IProductServices {
    List<Product> retrieveAllProducts();

    Product addAndUpdateProduct(MultipartFile image,Product product) throws IOException;


    Product retrieveProduct (Long productId);

    void deleteProduct(Long productId);

    List<Product> findProductByName(String productName);
    List<Product> filterProducts(String productName);
    Product update(Product product);
    List<Product> sortedprice();
    public byte[] product(Long productId) throws IOException, InterruptedException;

    Forbidden addForbidden(Forbidden b);

    /* ProductCategory TopProductCategoryByUserThisWeek (User u);// add by Oumaima majdoub for the daily offers mail*/
    /*Product createProduct(Product product);*/

}