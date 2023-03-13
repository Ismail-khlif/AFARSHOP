package pidev.afarshop.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pidev.afarshop.Entity.Bill;
import pidev.afarshop.Entity.Delivery;
import pidev.afarshop.Entity.Product;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill,Long> {


    @Query("select p from Product p join p.order o where o.orderId= :orderId ")
    List<Product> getProducts(@Param("orderId") Long orderId);


    @Query("SELECT b FROM Bill b join b.order1 o where o.user.UserId = :UserId")
    public List<Bill> retrieveBillByUser(@Param("UserId") Long UserId);

}
