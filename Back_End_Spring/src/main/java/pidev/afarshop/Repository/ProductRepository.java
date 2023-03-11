package pidev.afarshop.Repository;
 import org.springframework.data.jpa.repository.Query;
 import org.springframework.data.repository.query.Param;
 import org.springframework.mail.SimpleMailMessage;
 import pidev.afarshop.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 import java.awt.print.Pageable;
 import java.util.Date;
 import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
 /*Product createProduct(Product product);
*/

}
