package pidev.afarshop.Service.Product;


import pidev.afarshop.Entity.*;
import pidev.afarshop.Repository.*;
import pidev.afarshop.Service.ICRUDService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ProductServices implements IProductServices  {
    ProductRepository productRepository;

    @Override
    public List<Product> retrieveAllProducts() {
        return productRepository.findAll();
    }
    @Override
    public Product addAndUpdateProduct(MultipartFile image,Product product) throws IOException {
        product.setImages(image.getBytes());
        return productRepository.save(product);
    }
    @Override
    public Product retrieveProduct(Long productId) {
        return productRepository.findById(productId).get();
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
    //recherche
    public Product findProductByName(String productName){
        return productRepository.findByProductName(productName);
    }
    //filtre
    public List<Product> filterProducts(String productName) {
        return productRepository.findByProductNameContaining(productName);
    }
    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }


}