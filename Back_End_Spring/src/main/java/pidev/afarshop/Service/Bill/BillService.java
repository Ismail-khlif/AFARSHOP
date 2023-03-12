package pidev.afarshop.Service.Bill;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.afarshop.Entity.Bill;
import pidev.afarshop.Entity.Order1;
import pidev.afarshop.Repository.BillRepository;
import pidev.afarshop.Repository.Order1Repository;
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
    Order1Repository Order1Repository;




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
    public Bill addBill(Bill bill, Long Order1Id) {
        float amount=calculatePaymentAmount(Order1Id, bill);
        bill.setPaymentAmount(amount);
        Order1 Order1= Order1Repository.findById(Order1Id).orElse(null);
        bill.setOrder1(Order1);

        billRepository.save(bill);
        return bill;

    }

    @Override
    public float calculatePaymentAmount(Long Order1Id, Bill bill){

        float amount=0;
       /* List<Product> products=billRepository.getProducts(Order1Id);
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
        }*/
        return amount;

    }




}
