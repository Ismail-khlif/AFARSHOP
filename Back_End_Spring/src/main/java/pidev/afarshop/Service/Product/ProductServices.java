package pidev.afarshop.Service.Product;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pidev.afarshop.Entity.Product;
import pidev.afarshop.Entity.ProductCategory;
import pidev.afarshop.Entity.User;
import pidev.afarshop.Repository.ProductRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ProductServices implements IProductServices {
    ProductRepository productRepository;

    @Override
    public List<Product> retrieveAllProducts() {
        return productRepository.findAll();
    }
    @Override
    public Product addAndUpdateProduct(MultipartFile image, Product product) throws IOException {
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
    public List<Product> findByName(String name) {
        return productRepository.findByProductName(name);
    }


}