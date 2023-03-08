package pidev.afarshop.Service.Product;


import pidev.afarshop.Entity.*;
import pidev.afarshop.Repository.*;
import pidev.afarshop.Service.ICRUDService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.net.http.*;
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
    public List<Product> sortedprice(){
        return productRepository.findAllByOrderByPrice();
    }

    @Override
    public byte[] product(Long productId) throws IOException, InterruptedException{
        Product product = productRepository.findById(productId).get();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://yakpdf.p.rapidapi.com/pdf"))
                .header("content-type", "application/json")
                .header("X-RapidAPI-Host", "yakpdf.p.rapidapi.com")
                .header("X-RapidAPI-Key", "b648c42070msh2f1e24111397e42p1155f4jsn864d7705eee5")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\r\n    \"pdf\": {\r\n        \"format\": \"A4\",\r\n        \"printBackground\": true,\r\n        \"scale\": 1\r\n    },\r\n    \"source\": {\r\n        \"html\": \"<!DOCTYPE html><html lang=\\\"en\\\"><head><meta charset=\\\"UTF-8\\\"><meta name=\\\"viewport\\\" content=\\\"width=device-width, initial-scale=1.0\\\"></head><body><div style=\\\"width:800px; height:600px; padding:20px; text-align:center; border: 10px solid #DB7093\\\"><div style=\\\"width:750px; height:550px; padding:20px; text-align:center; border: 5px solid #FFC0CB\\\"><span style=\\\"font-size:50px; font-weight:bold\\\">Certificate of Completion</span><br><br><span style=\\\"font-size:25px\\\"><i>This is to certify that</i></span><br><br><span style=\\\"font-size:30px\\\"><b>"+product.getPrice()+"</b></span><br/><br/><span style=\\\"font-size:25px\\\"><i>has completed the course</i></span> <br/><br/><span style=\\\"font-size:30px\\\"> "+product.getProductName()+"</span> <br/><br/><br/><br/><br/><br/><span style=\\\"font-size:25px\\\"><i>For "+product.getProductId()+"hours length</i></span><br><span style=\\\"font-size:25px;float:left\\\">Aquired on : "+product.getDateOfProduct()+"</span><div style=\\\"float:right\\\"><img src=\\\""+product.getBrand()+"\\\"></div></div></div></body></html>\"\r\n    },\r\n    \"wait\": {\r\n        \"for\": \"navigation\",\r\n        \"timeout\": 250,\r\n        \"waitUntil\": \"load\"\r\n    }\r\n}"))
                .build();
        HttpResponse<byte[]> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofByteArray());
        byte[] res = response.body();
        return res;
    }

}