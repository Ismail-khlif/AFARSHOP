package pidev.afarshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidev.afarshop.Entity.*;

@Repository
public interface OrderRepository extends JpaRepository<Order1,Long> {
    // Show Provider name with their orders
    /*
    @Query("SELECT p.providerName as n FROM Provider p JOIN p.deliveries d JOIN d.orders ")
    List<String> findProviderNamesWithOrder();

     */

    //Show Orders list of every Provider
   /* List<Order1> findByDelivery_Provider_ProviderName(String name);*/

}