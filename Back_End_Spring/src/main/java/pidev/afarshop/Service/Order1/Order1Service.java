package pidev.afarshop.Service.Order1;

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
public class Order1Service implements ICRUDService<Order1,Long> , IOrderServices {

    Order1Repository orderRepository;
    @Override
    public List<Order1> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order1 retrieveItem(Long idItem) {
        return orderRepository.findById(idItem).get();
    }

    @Override
    public Order1 add(Order1 order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(Long orderId) {
        orderRepository.deleteById(orderId);

    }

    @Override
    public Order1 update(Order1 order) {
        return orderRepository.save(order);
    }
}