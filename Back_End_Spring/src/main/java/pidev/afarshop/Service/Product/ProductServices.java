package pidev.afarshop.Service.Product;

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

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ProductServices implements IProductServices {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    Order1Repository order1Repository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private MailService senderService;

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