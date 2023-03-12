package pidev.afarshop.Service.Payement;

import pidev.afarshop.Entity.*;
import pidev.afarshop.Service.*;
import pidev.afarshop.Repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PaymentServices implements ICRUDService<Payment,Long> , IPaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment retrieveItem(Long idItem) {
        return paymentRepository.findById(idItem).get();
    }

    @Override
    public Payment add(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public void delete(Long payementId) {

        paymentRepository.deleteById(payementId);

    }

    @Override
    public Payment update(Payment payment) {
        return paymentRepository.save(payment);
    }
}