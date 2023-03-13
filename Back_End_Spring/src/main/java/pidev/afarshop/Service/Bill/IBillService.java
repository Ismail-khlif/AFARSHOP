package pidev.afarshop.Service.Bill;

import pidev.afarshop.Entity.Bill;
import java.util.List;

public interface IBillService {

    List<Bill> retrieveAllBills();
    Bill retrieveBillById(Long billId);
    Bill addBill(Bill bill, Long orderId);
    float calculatePaymentAmount(Long orderId, Bill bill);
    List<Bill> retrieveBillByUser();



}
