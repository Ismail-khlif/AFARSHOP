package pidev.afarshop.Service.Product;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pidev.afarshop.Entity.*;
import pidev.afarshop.Repository.*;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pidev.afarshop.Entity.Product;
import pidev.afarshop.Repository.Order1Repository;
import pidev.afarshop.Repository.ProductRepository;
import pidev.afarshop.Repository.UserRepository;
import pidev.afarshop.Service.Mail.MailService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
@AllArgsConstructor

public class ProductServices implements IProductServices  {
    StoreRepository storeRepository;
    ProductRepository productRepository;
    ForbiddenRepository  forbiddenRepo;
    UserRepository userRepository;
    ProductCommentRepository productCommentRepository ;
    CategoryAdverRepo categoryAdverRepo ;
    UserDataLoadRepo userDataLoadRepo;
    StoreLocationsRepository storeLocationsRepository;


    @Autowired
    Order1Repository order1Repository;
    
    @Autowired
    private MailService senderService;


    ProductLikeRepository productLikeRepository;
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

    //recherche
    public List<Product> findProductByName(String productName){
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
                .method("Product", HttpRequest.BodyPublishers.ofString("{\r\n    \"pdf\": {\r\n        \"format\": \"A4\",\r\n        \"printBackground\": true,\r\n        \"scale\": 1\r\n    },\r\n    \"source\": {\r\n        \"html\": \"<!DOCTYPE html><html lang=\\\"en\\\"><head><meta charset=\\\"UTF-8\\\"><meta name=\\\"viewport\\\" content=\\\"width=device-width, initial-scale=1.0\\\"></head><body><div style=\\\"width:800px; height:600px; padding:20px; text-align:center; border: 10px solid #DB7093\\\"><div style=\\\"width:750px; height:550px; padding:20px; text-align:center; border: 5px solid #FFC0CB\\\"><span style=\\\"font-size:50px; font-weight:bold\\\">Certificate of Completion</span><br><br><span style=\\\"font-size:25px\\\"><i>This is to certify that</i></span><br><br><span style=\\\"font-size:30px\\\"><b>"+product.getPrice()+"</b></span><br/><br/><span style=\\\"font-size:25px\\\"><i>has completed the course</i></span> <br/><br/><span style=\\\"font-size:30px\\\"> "+product.getProductName()+"</span> <br/><br/><br/><br/><br/><br/><span style=\\\"font-size:25px\\\"><i>For "+product.getProductId()+"hours length</i></span><br><span style=\\\"font-size:25px;float:left\\\">Aquired on : "+product.getDateOfProduct()+"</span><div style=\\\"float:right\\\"><img src=\\\""+product.getBrand()+"\\\"></div></div></div></body></html>\"\r\n    },\r\n    \"wait\": {\r\n        \"for\": \"navigation\",\r\n        \"timeout\": 250,\r\n        \"waitUntil\": \"load\"\r\n    }\r\n}"))
                .build();
        HttpResponse<byte[]> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofByteArray());
        byte[] res = response.body();
        return res;
    }

    @Override
    public Forbidden addForbidden(Forbidden b) {

            return forbiddenRepo.save(b);
        }
        public int Filtrage_bad_word(String ch) {
        int x = 0;
        List<Forbidden> l1 = (List<Forbidden>) forbiddenRepo.findAll();
        for (Forbidden badWord : l1) {
            // if (badWord.getWord().contains(ch))
            if (ch.contains(badWord.getText()) == true)
                x = 1;
        }
        return x;

    }
    public ResponseEntity<?> addComment_to_Product(ProductComment ProductComment, Long idProduct, Long idUser) {
        Product p = productRepository.findById(idProduct).orElse(null);
        User u = userRepository.findById(idUser).orElse(null);
        DetctaDataLoad(ProductComment.getCommentBody(),idUser);
        if (Filtrage_bad_word(ProductComment.getCommentBody()) == 0) {
            ProductComment.setUser(u);
            ProductComment.setProduct(p);

            productCommentRepository.save(ProductComment);
            return ResponseEntity.ok().body(ProductComment);      }else
            /*
             * Set<ProductComment> pc = p.getProductComments(); pc.add(ProductComment);
             * p.setProductComments(pc); ProductRepo.save(p);
             *
             * Set<ProductComment> pu = u.getProductComments(); pu.add(ProductComment);
             * u.setProductComments(pu); userRepo.save(u);
             *
             *
             */
            //}
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Bads Word Detected");
    }

    public void DetctaDataLoad (String ch , Long idUser) {

        List<UserDataLoad> ul = userDataLoadRepo.findAll();
        User u = userRepository.findById(idUser).orElse(null);
        for (CategoryAdve string : categoryAdverRepo.findAll()) {
            if (ch.contains(string.getNameCategory())) {
                if (existDataForUser(string.getNameCategory(),idUser) == true) {
                    UserDataLoad l = getData(string.getNameCategory(),idUser);
                    l.setNbrsRequet(l.getNbrsRequet()+1);
                    userDataLoadRepo.save(l);
                }
                else {
                    UserDataLoad l1 = new UserDataLoad();
                    l1.setCategorieData(string.getNameCategory());
                    l1.setUser(u);
                    l1.setNbrsRequet(1);
                    userDataLoadRepo.save(l1);

                }
            }
        }
    }

    public Boolean existDataForUser(String ch,Long IdUser) {
        Boolean x = false;
        for (UserDataLoad userDataLoad : userDataLoadRepo.findAll()) {
            if (userDataLoad.getCategorieData().equals(ch) && userDataLoad.getUser().getUserId() == IdUser) {
                x = true;
            }
        } return x;
    }
    public UserDataLoad getData(String ch,Long IdUser) {
        UserDataLoad x = null;
        for (UserDataLoad userDataLoad : userDataLoadRepo.findAll()) {
            if (userDataLoad.getCategorieData().equals(ch) && userDataLoad.getUser().getUserId() == IdUser) {
                x = userDataLoad;
            }
        } return x;
    }
    public Map<String ,StoreLocations> getNearestStorewithproduct(Long productId, double clientLatitude,
                                                                   double clientLongitude){

        Product product = productRepository.findById(productId).get();

       // StoreLocations  storeLocations = null;
        double shortestDistance = Double.MAX_VALUE;
        String storeName = null;
        Map<String,StoreLocations> map= new HashMap<>();
        for (StoreLocations store1 : product.getStore().getStoreLocations()) {
            double distance = distanceInKm(clientLatitude, clientLongitude, store1.getLatitude(),
                    store1.getLongitude());
            if (distance <= shortestDistance) {
                shortestDistance = distance;
                //storeLocations = store1 ;
                storeName = product.getStore().getStoreName();
                map.put(storeName,store1);
            }
        }
        return map;
    }
    private double distanceInKm(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344; // convert to kilometers
        return dist;
    }


    public void AssignLocationtoStore(Long locationId, Long  StoreId) {
        Store store = storeRepository.findById(StoreId).get();
        StoreLocations storeLocations = storeLocationsRepository.findById(locationId).get();

       // List<StoreLocations> locationsList = new ArrayList<>();
       // locationsList.add(storeLocations);

        store.getStoreLocations().add(storeLocations);
        storeRepository.save(store);
    }

    public ProductLike addLike_to_Post(ProductLike productLike, Long idProduct, Long idUser) {
        int x=0;
        boolean y =false;
        Product p = productRepository.findById(idProduct).orElse(null);
        User u = userRepository.findById(idUser).orElse(null);
        for (ProductLike l : productLikeRepository.findAll()) {
            if(l.getProduct().getProductId() == idProduct && l.getUser().getUserId() == idUser)
            {
                x=1;
                y=l.getIsLiked();
                productLikeRepository.delete(l);
            }

        }
        if (x ==0 || (x == 1 && y!=productLike.getIsLiked()	)) {
            DetctaDataLoad(p.getDescription(),idUser);
            productLike.setUser(u);
            productLike.setProduct(p);
            productLike.setLikedAt( LocalDate.now());
            productLikeRepository.save(productLike);}
        return productLike;
    }
    public Store getStoreByProductId(Long productId){
        List<Store> stores= storeRepository.findAll();
        Product product=productRepository.findById(productId).get();
        for(Store store:stores){
            for(Product p: store.getProducts()){
                if(p.equals(product)){
                    return store;
                }
            }
        }
        return null;
    }

/////////OUmaima///////////while merging//=======
    /*public List<Product> findByName(String name) {
        return productRepository.findByProductName(name);
    }*/
    //notif

   /* public Product createProduct(Product product) {
        // Code pour créer un nouveau produit

        // Récupérer la liste des utilisateurs
        List<User> users = userRepository.findAll();

        // Envoyer un e-mail à chaque utilisateur
        for (User user : users) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getemail());
            message.setSubject("Nouveau produit ajouté");
            message.setText("Un nouveau produit a été ajouté à notre catalogue. Consultez notre site pour plus d'informations.");
            productRepository.send(message);
        }

        return product;
    }
*/




    }





