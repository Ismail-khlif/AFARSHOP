package pidev.afarshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidev.afarshop.Entity.*;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order1,Long> {


}