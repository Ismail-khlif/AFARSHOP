package pidev.afarshop.Controller.Product;

import com.google.zxing.WriterException;
import org.hibernate.boot.archive.scan.spi.ScanOptions;
import org.springframework.beans.factory.annotation.Autowired;
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

import pidev.afarshop.Controller.Chatgbt.CompletionRequest;
import pidev.afarshop.Controller.Chatgbt.CompletionResponse;
import pidev.afarshop.Controller.Chatgbt.OpenAiApiClient;
import com.fasterxml.jackson.databind.ObjectMapper;

import springfox.documentation.annotations.ApiIgnore;

import javax.transaction.Transactional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")

public class ProductController {
    ProductServices productServices;
    ProductRepository productRepository;

    @Autowired
    private ObjectMapper jsonMapper;
    @Autowired private OpenAiApiClient client;

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


        Product product1= new Product();
        product1.setProductId((long)20);
        product1.setProductName("dsf");
        product1.setBrand("brand");
        product1.setImages(null);
        product1.setReference("reference");
        product1.setDescription("description");
        product1.setQuantity(2L);
        product1.setVideo(null);
        product1.setPrice(25);
        product1.setDateOfProduct(null);
        product1.setDiscount(1);
        product1.setYearsOfWarranty(2);
        product1.setRating(23);

        productByStore.put("hello", product1);

        for (Map.Entry<String, Product> entry : productByStore.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }



        System.out.println("------------------------------------------------------------------------------------------");

        for(Product product:productsByName){
            System.out.println("-----------------product.getStore().getStoreName()-------------------"+product.getStore().getStoreName());
            productByStore.put(product.getStore().getStoreName(), product);

        }
        System.out.println("----------------------------------result--------------------------------------------------------");

        for (Map.Entry<String, Product> entry : productByStore.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        return productByStore;
    }
    @GetMapping("/getstore/{pid}")
    public Store getStoreByProductId(@PathVariable("pid") Long productId){
        return productServices.getStoreByProductId(productId);
    }

    @GetMapping("/findmatchingaiproducts")
    public ResponseEntity<List<Product>> chatWithGpt3(@RequestParam String message) throws Exception {
        var completion = CompletionRequest.defaultWith("give me a list products which name is "+message);
        System.out.println("-------------------------------completion------------------------------------"+completion);
        var postBodyJson = jsonMapper.writeValueAsString(completion);
        System.out.println("-------------------------------postBodyJson------------------------------------"+postBodyJson);
        var responseBody = client.postToOpenAiApi(postBodyJson, OpenAiApiClient.OpenAiService.GPT_3);
        System.out.println("-------------------------------responseBody------------------------------------"+responseBody);
        var completionResponse = jsonMapper.readValue(responseBody, CompletionResponse.class);
        System.out.println("-------------------------------completionResponse------------------------------------"+completionResponse);
        List<Product> products=productRepository.findAll();
        for(Product product:products){
        System.out.println("-------------------------------products------------------------------------"+product.getProductId());}
        var result = completionResponse.firstAnswer().trim();
        System.out.println("-------------------------------result------------------------------------"+result);
        List<String> resultList = Arrays.asList(result.split("\n"));
        System.out.println("-------------------------------resultList------------------------------------"+resultList);
        resultList.replaceAll(s -> s.replaceAll("^\\d+\\.\\s*", ""));
        System.out.println("-------------------------------resultList---with replace---------------------------------"+resultList);
        List<Product> resultRes =new ArrayList<>();
        for (String l:resultList){
            System.out.println("------------------------for-------resultList------------------------------------"+l);
            for (Product product:products){
                System.out.println("-------------------------------product------------------------------------"+product.getProductName());
                if(l.contains(product.getProductName())){
                  System.out.println("-------------------------------ajout final------------------------------------");

                    resultRes.add(product);
                }
            }
        }
        return ResponseEntity.ok(resultRes);
    }
    @GetMapping("/AnalyzeProductComments")
    public Map<String,List<Float>> analizeSentimentOfComments(){
        return productServices.analizeSentimentOfComments();
    }
}