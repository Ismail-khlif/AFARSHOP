package pidev.afarshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidev.afarshop.Entity.*;

@Repository
public interface Order1Repository extends JpaRepository<Order1,Long> {


}