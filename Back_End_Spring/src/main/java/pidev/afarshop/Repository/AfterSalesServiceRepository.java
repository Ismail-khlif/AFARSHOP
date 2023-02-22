package pidev.afarshop.Repository;

import pidev.afarshop.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AfterSalesServiceRepository  extends JpaRepository<AfterSalesService,Long> {
}