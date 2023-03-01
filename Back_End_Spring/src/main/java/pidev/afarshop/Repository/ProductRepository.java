package pidev.afarshop.Repository;
 import org.springframework.web.bind.annotation.PathVariable;
 import pidev.afarshop.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
        //recherche
        Product findByProductName(String productName);
        // Renvoie tous les produits dont le nom contient la chaîne spécifiée
        //filtre
        List<Product> findByProductNameContaining(String productName);
}
