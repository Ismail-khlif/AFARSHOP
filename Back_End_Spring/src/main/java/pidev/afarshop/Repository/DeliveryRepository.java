package pidev.afarshop.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pidev.afarshop.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,Long> {

    @Query("SELECT d FROM Delivery d join d.order o where o.user.UserId = :UserId")
    public List<Delivery> retrieveDeliveryByUser(@Param("UserId") Long UserId);

    @Query("SELECT COUNT(d) FROM Delivery d WHERE d.creationDate between :startDate and :endDate and d.deliveryMode = 'Home' ")
    int countHomeDeliveryByCreationDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    @Query("SELECT COUNT(d) FROM Delivery d WHERE d.creationDate between :startDate and :endDate and d.deliveryMode = 'Store' ")
    int countStoreDeliveryByCreationDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}