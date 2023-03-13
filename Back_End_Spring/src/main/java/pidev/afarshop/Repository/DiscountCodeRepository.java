package pidev.afarshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pidev.afarshop.Entity.*;
import java.util.Date;
import java.util.List;
@Repository
public interface DiscountCodeRepository extends JpaRepository<DiscountCode,Long> {

    DiscountCode findByCode(String code);
}