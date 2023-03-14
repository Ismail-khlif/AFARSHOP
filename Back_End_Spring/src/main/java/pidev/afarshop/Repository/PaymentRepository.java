package pidev.afarshop.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pidev.afarshop.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    @Query("SELECT p FROM Payment p where p.billPayment.billId= :billId")
    List<Payment> findPaymentByBillId(@Param("billId") Long billId);

    List<Payment> findByDueDateBeforeAndPaidIsFalse(Date today);
}
