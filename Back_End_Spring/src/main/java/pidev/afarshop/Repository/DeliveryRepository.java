package pidev.afarshop.Repository;
import pidev.afarshop.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,Long> {
}