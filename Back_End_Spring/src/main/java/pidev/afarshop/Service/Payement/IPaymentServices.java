package pidev.afarshop.Service.Payement;


import pidev.afarshop.Entity.Delivery;
import pidev.afarshop.Entity.Payment;

import java.util.List;

public interface IPaymentServices {

    List<Payment> retrieveAllPayments();
    Payment addPayment(Payment payment);
    void deletePayment(Long id);
    Payment updatePayment(Payment payment);




}


