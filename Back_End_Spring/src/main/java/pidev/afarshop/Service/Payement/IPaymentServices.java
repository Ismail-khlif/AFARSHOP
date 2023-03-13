package pidev.afarshop.Service.Payement;

import pidev.afarshop.Entity.Payment;

import java.util.List;

public interface IPaymentServices {

    List<Payment> retrieveAllPayments();
    Payment addPayment(Payment payment, Long idBill);
    void deletePayment(Long id);
    Payment updatePayment(Long idPayment);
    List<Payment> retrievePaymentsByBill(Long idBill);

    void alertPayment();




}

