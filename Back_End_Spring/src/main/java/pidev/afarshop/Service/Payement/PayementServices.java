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
public class PayementServices implements ICRUDService<Payement,Long> , IPayementServices {

    PayementRepository payementRepository;

    @Override
    public List<Payement> findAll() {
        return payementRepository.findAll();
    }

    @Override
    public Payement retrieveItem(Long idItem) {
        return payementRepository.findById(idItem).get();
    }

    @Override
    public Payement add(Payement payement) {
        return payementRepository.save(payement);
    }

    @Override
    public void delete(Long payementId) {

        payementRepository.deleteById(payementId);

    }

    @Override
    public Payement update(Payement payement) {
        return payementRepository.save(payement);
    }
}