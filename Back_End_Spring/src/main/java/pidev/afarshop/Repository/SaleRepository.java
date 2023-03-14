package pidev.afarshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pidev.afarshop.Entity.Product;
import pidev.afarshop.Entity.Sale;

public interface SaleRepository extends JpaRepository<Sale,Long> {
    @Query("SELECT SUM(s.quantity * s.price) FROM Sale s WHERE s.product.productId = :productId")
    Double findTotalRevenueByProduct(@Param("productId") Long productId);
}
