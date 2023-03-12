
package pidev.afarshop.Service.Payement;


import org.springframework.beans.factory.annotation.Autowired;




import pidev.afarshop.Entity.*;
import pidev.afarshop.Service.*;
import pidev.afarshop.Repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import pidev.afarshop.Service.Payement.IPaymentServices;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PaymentServices  {
  /*  private final BillRepository billRepository;


    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public List<Payment> retrieveAllPayments() {
         return paymentRepository.findAll();
    }

    @Transactional
    public void chooseMethod(Payment p){
        if (p.getPaymentMethod()==PaymentMethod.Cash)
        {
            addPayment(p,idBill);
        }
        else if (p.getPaymentMethod()==PaymentMethod.Card){
            
        }
    }

    @Override
    public Payment addPayment(Payment payment, Long idBill) {
        Bill bill = billRepository.findById(idBill).orElse(null);
        if (bill.getInstallmentsNb()==1){
            payment.setPaid(true);
            payment.setInstallmentAmount(bill.getPaymentAmount());
            payment.setBillPayment(bill);
            paymentRepository.save(payment);

            if (bill.getInstallmentsNb()==12)
            {

                for (int i=1;i<=12;i++)
                {
                    Payment p1 = new Payment();
                    p1.setBillPayment(bill);
                    p1.setInstallmentAmount(bill.getPaymentAmount()/12);
                    paymentRepository.save(p1);
                }
            }

            if (bill.getInstallmentsNb()==4)
            {

                for (int i=1;i<=4;i++)
                {
                    Payment p2 = new Payment();
                    p2.setBillPayment(bill);
                    p2.setInstallmentAmount(bill.getPaymentAmount()/4);
                    paymentRepository.save(p2);
                }
            }
            return payment;

            }
        }






    }

    @Override
    public void deletePayment(Long id) {
    }

    @Override
    public Payment updatePayment(Payment payment) {
        return null;
    } */
}

