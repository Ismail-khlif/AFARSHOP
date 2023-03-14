package pidev.afarshop.Repository;
 import org.springframework.data.jpa.repository.Query;
 import org.springframework.data.repository.query.Param;
 import org.springframework.web.bind.annotation.PathVariable;
 import pidev.afarshop.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
        //recherche
        List<Product> findByProductName(String productName);
        // Renvoie tous les produits dont le nom contient la chaîne spécifiée
        //filtre
        List<Product> findByProductNameContaining(String productName);
        //tri
        List<Product> findAllByOrderByPrice();
    @Query("SELECT SUM(s.quantity * s.price) FROM Sale s WHERE s.product.productId = :productId")
    Double findTotalRevenueByProduct(@Param("productId") Long productId);
}
