package pidev.afarshop.Service.Bill;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.afarshop.Entity.Bill;
import pidev.afarshop.Entity.Delivery;
import pidev.afarshop.Entity.Order;
import pidev.afarshop.Entity.Product;
import pidev.afarshop.Repository.BillRepository;
import pidev.afarshop.Repository.OrderRepository;
import pidev.afarshop.Repository.PaymentRepository;

import java.util.List;
@Service
@Slf4j
@AllArgsConstructor
public class BillService implements IBillService {

    @Autowired
    BillRepository billRepository;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    OrderRepository orderRepository;




    @Override
    public List<Bill> retrieveAllBills()
    {
        return (List<Bill>) billRepository.findAll();
    }

    @Override
    public Bill retriveBillById(Long billId) {
        return billRepository.findById(billId).orElse(null);
    }

    @Override
    public Bill addBill(Bill bill, Long orderId) {
        float amount=calculatePaymentAmount(orderId, bill);
        bill.setPaymentAmount(amount);
        Order order= orderRepository.findById(orderId).orElse(null);
        bill.setOrder(order);

        billRepository.save(bill);
        return bill;

    }

    @Override
    public float calculatePaymentAmount(Long orderId, Bill bill){

        float amount=0;
        List<Product> products=billRepository.getProducts(orderId);
        for (Product product: products){
            if(product.isFacility())
            {
                if ( bill.getInstallmentsNb()==4){
                    amount=amount+(product.getPrice()*(110/100));
                }
                if ( bill.getInstallmentsNb()==12) {
                    amount=amount+(product.getPrice()*(120/100));
                }
                else {
                    amount=amount+(product.getPrice());
                }
            }
            else {
                amount=amount+(product.getPrice());

            }
        }
        return amount;

    }




}
