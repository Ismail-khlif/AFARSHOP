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

    PayementRepository payementRepository;

    @Override
    public List<Payment> findAll() {
        return payementRepository.findAll();
    }

    @Override
    public Payment retrieveItem(Long idItem) {
        return payementRepository.findById(idItem).get();
    }

    @Override
    public Payment add(Payment payment) {
        return payementRepository.save(payment);
    }

    @Override
    public void delete(Long payementId) {

        payementRepository.deleteById(payementId);

    }

    @Override
    public Payment update(Payment payment) {
        return payementRepository.save(payment);
    }
}