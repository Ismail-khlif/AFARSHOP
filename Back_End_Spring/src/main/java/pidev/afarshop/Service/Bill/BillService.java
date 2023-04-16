package pidev.afarshop.Service.Bill;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import pidev.afarshop.Entity.*;


import pidev.afarshop.Repository.BillRepository;
import pidev.afarshop.Repository.Order1Repository;
import pidev.afarshop.Repository.PaymentRepository;
import pidev.afarshop.Service.Delivery.DeliveryService;

import java.util.Calendar;
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



    Order1Repository order1Repository;

    @Autowired
    DeliveryService deliveryService;





    //@Override
    //public List<Bill> retrieveAllBills() {return (List<Bill>) billRepository.findAll();}

    @Override
    public Bill retrieveBillById(Long billId) {
        return billRepository.findById(billId).orElse(null);
    }

    @Override
    public Bill addBill(Bill bill, Long Order1Id) {
        double amount=calculatePaymentAmount(Order1Id, bill);
        bill.setPaymentAmount(amount);


        bill.setBillDate(Calendar.getInstance().getTime());
        Order1 order1 = order1Repository.findById(Order1Id).orElse(null);
        order1.setBill(bill);

        billRepository.save(bill);
        return bill;

    }

    @Override
    public double calculatePaymentAmount(Long Order1Id, Bill bill){

        double amount=0;
        List<Product> products=billRepository.getProducts(Order1Id);
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

    @Override
    public List<Bill> retrieveBillByUser() {
        User user = deliveryService.retrieveConnectedUser();
        long Id = user.getUserId();
        List<Bill> bill = billRepository.retrieveBillByUser(Id);
        return bill;
    }




}
