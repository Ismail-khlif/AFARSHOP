package pidev.afarshop.Repository;
 import org.springframework.data.jpa.repository.Query;
 import org.springframework.data.repository.query.Param;
 import pidev.afarshop.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 import java.awt.print.Pageable;
 import java.util.Date;
 import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
 List<Product> findByProductName(String productName);
 /*
 @Query("SELECT p.productCategory FROM Order1 o JOIN o.cart.products p WHERE o.payement.user.id = :userId " +
         "AND o.orderDate BETWEEN :startOfWeek AND :endOfWeek GROUP BY p.productCategory " +
         "ORDER BY COUNT(p.productCategory) DESC")
 ProductCategory findTopProductCategoryByUserThisWeek(Long userId, Date startOfWeek, Date endOfWeek);
 @Query("SELECT p FROM Product p WHERE p.productCategory = :category ORDER BY p.createdAt DESC")
 List<Product> findTop4ProductsByCategoryOrderByRecentlyAdded(@Param("category") ProductCategory category, Pageable pageable);
*/
}
