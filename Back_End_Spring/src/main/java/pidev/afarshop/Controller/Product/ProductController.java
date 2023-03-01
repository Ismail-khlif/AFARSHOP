package pidev.afarshop.Controller.Product;

import org.springframework.format.annotation.DateTimeFormat;
import pidev.afarshop.Entity.*;
import pidev.afarshop.Repository.*;

import pidev.afarshop.Service.Product.ProductServices;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    ProductServices productServices;
    ProductRepository productRepository;

    @PostMapping(value = "/addandupdateproduct" ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Product addProduct(@RequestParam("image") MultipartFile image, @RequestParam("productName") String poductName,
                              @RequestParam("reference") String reference,@RequestParam("description") String description,
                              @RequestParam("quantity") Long quantity,@RequestParam("rating") float rating,
                              @RequestParam("video") MultipartFile video,@RequestParam("price") float price,
                              @RequestParam("date")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfProduct,
                              @RequestParam("discount") float discount,@RequestParam("brand") String brand,
                              @RequestParam("yearsOfWarranty") int yearsOfWarranty) throws IOException {

        Product product = new Product();
        product.setProductName(poductName);
        product.setBrand(brand);
        product.setImages(image.getBytes());
        product.setReference(reference);
        product.setDescription(description);
        product.setQuantity(quantity);
        product.setVideo(video.getBytes());
        product.setPrice(price);
        product.setDateOfProduct(dateOfProduct);
        product.setDiscount(discount);
        product.setYearsOfWarranty(yearsOfWarranty);
        product.setRating(rating);
        productRepository.save(product);
        return product;
    }
    @GetMapping("/retriveproduct/{id}")
    public Product retrieveProduct (@PathVariable("id") Long productId){
        return productServices.retrieveProduct(productId);
    }
    @GetMapping("/retrieveallproducts")
    public List<Product> retrieveAllProducts(){
        return  productServices.retrieveAllProducts();
    }
    @DeleteMapping("/deleteproduct/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        productServices.deleteProduct(id);
    }
    //recherche
    @GetMapping("/findProductByName/{ProductName}")
    public Product findProductByName(@PathVariable("ProductName") String ProductName){
        return productServices.findProductByName(ProductName);
    }
    @PutMapping(value ="/updateProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Product update (@RequestParam("productId") Long productId ,
                           @RequestParam("image") MultipartFile image, @RequestParam("productName") String poductName,
                           @RequestParam("reference") String reference,@RequestParam("description") String description,
                           @RequestParam("quantity") Long quantity,@RequestParam("rating") float rating,
                           @RequestParam("video") MultipartFile video,@RequestParam("price") float price,
                           @RequestParam("date")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfProduct,
                           @RequestParam("discount") float discount,@RequestParam("brand") String brand,
                           @RequestParam("yearsOfWarranty") int yearsOfWarranty) throws IOException {

        Product product = new Product();

        product.setProductId(productId);
        product.setProductName(poductName);
        product.setBrand(brand);
        product.setImages(image.getBytes());
        product.setReference(reference);
        product.setDescription(description);
        product.setQuantity(quantity);
        product.setVideo(video.getBytes());
        product.setPrice(price);
        product.setDateOfProduct(dateOfProduct);
        product.setDiscount(discount);
        product.setYearsOfWarranty(yearsOfWarranty);
        product.setRating(rating);
        productRepository.save(product);
        return product;
    }
    //filtre
    @GetMapping("/filterbyName/{ProductName}")
    public List<Product> filterProducts(@PathVariable("ProductName") String ProductName){return productServices.filterProducts(ProductName);}
    //tri

}