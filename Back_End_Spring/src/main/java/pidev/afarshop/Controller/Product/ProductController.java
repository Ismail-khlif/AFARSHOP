package pidev.afarshop.Controller.Product;


import com.google.zxing.WriterException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import pidev.afarshop.Entity.*;
import pidev.afarshop.Repository.*;

import pidev.afarshop.Service.Product.ProductServices;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pidev.afarshop.utils.QRCodeGenerator;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipal;
import java.time.LocalDate;
import java.util.*;

import springfox.documentation.annotations.ApiIgnore;

import javax.transaction.Transactional;

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
                              @RequestParam("yearsOfWarranty") int yearsOfWarranty) throws IOException, WriterException {

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
        QRCodeGenerator.generateQRCode(product);
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
    public List<Product> findProductByName(@PathVariable("ProductName") String ProductName){
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

        //update working
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
    @GetMapping("/sortedbyprice")
    public List<Product> sortedprice(){return productServices.sortedprice();}
    @PostMapping(path="certifGen/{certificate}")
    public ResponseEntity<byte[]> product(@PathVariable("certificate") Long prodid) throws IOException, InterruptedException{
        Long certi = prodid.longValue();
        byte[] res = productServices.product(certi);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Certificate.pdf")
                .contentType(MediaType.APPLICATION_PDF).body(res);
    }//here
    @PostMapping("/add-Comment/{IdPost}/{IdUser}")
    @ResponseBody
    public ResponseEntity<?> addComment_to_Post(@RequestBody ProductComment productComment, @PathVariable("IdPost") Long IdPost,@PathVariable("IdUser") User u) {
        productComment.setCommentedAt(java.sql.Date.valueOf(LocalDate.now()))	;

        return productServices.addComment_to_Product(productComment,IdPost,u.getUserId());
    }

    @PostMapping("/AssignLocationtoStore/{IdLocation}/{IdStore}")
    public void AssignLocationToStore ( @PathVariable("IdLocation") Long IdLocation,@PathVariable("IdStore" )Long IdStore){
        productServices.AssignLocationtoStore(IdLocation,IdStore);

    }

    @GetMapping("/NearestStorewithproduct/{productId}/{lat}/{long}")
    public Map<String ,StoreLocations> getNearestStorewithproduct(@PathVariable("productId") Long productId,@PathVariable("lat") double clientLatitude,
                                                                  @PathVariable("long") double clientLongitude){

    return productServices.getNearestStorewithproduct(productId,clientLatitude,clientLongitude);

    }

    @PostMapping("/add-Like-post/{IdProduct}/{IdUser}")
    @ResponseBody
    public ProductLike addLike_to_Post(@RequestBody(required = false) ProductLike postLike, @PathVariable("IdProduct") Long IdProduct, @PathVariable("IdUser") User u) {
        ProductLike pos1 = new ProductLike();
        pos1.setIsLiked(true);

        return productServices.addLike_to_Post(pos1,IdProduct,u.getUserId());
    }
    @PostMapping("/add-DisLike-post/{IdProduct}/{IdUser}")
    @ResponseBody
    public ProductLike addDisLike_to_Post(@RequestBody(required = false) ProductLike postLike, @PathVariable("IdProduct") Long IdProduct, @PathVariable("IdUser") User u) {
        ProductLike pos1 = new ProductLike();
        pos1.setIsLiked(false);

        return productServices.addLike_to_Post(pos1,IdProduct,u.getUserId());
    }
    @GetMapping("/compare/{product}")
    public Map<String,Product> comparePrices(@PathVariable("product") String productName) {
        List<Product> productsByName = productRepository.findByProductName(productName);
        productsByName.sort(Comparator.comparing(Product::getPrice));
        Map<String,Product> productByStore=new HashMap<>();



        for(Product product:productsByName){
            productByStore.put(productServices.getStoreByProductId(product.getProductId()).getStoreName(), product);

        }
        return productByStore;
    }
    @GetMapping("/getstore/{pid}")
    public Store getStoreByProductId(@PathVariable("pid") Long productId){
        return productServices.getStoreByProductId(productId);
    }

    /*@PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product newProduct = productServices.createProduct(product);
        return ResponseEntity.ok(newProduct);
    }*/

}