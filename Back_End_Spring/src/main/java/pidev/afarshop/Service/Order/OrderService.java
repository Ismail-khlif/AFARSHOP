package pidev.afarshop.Service.Order;

import pidev.afarshop.Entity.*;
import pidev.afarshop.Service.*;
import pidev.afarshop.Repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class OrderService implements ICRUDService<Order1,Long> , IOrderServices {

    OrderRepository orderRepository;
    @Override
    public List<Order1> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order1 retrieveItem(Long idItem) {
        return orderRepository.findById(idItem).get();
    }

    @Override
    public Order1 add(Order1 order1) {
        return orderRepository.save(order1);
    }

    @Override
    public void delete(Long orderId) {
        orderRepository.deleteById(orderId);

    }

    @Override
    public Order1 update(Order1 order1) {
        return orderRepository.save(order1);
    }

    @Override
    public Map<String, List<Order1>> displayOrdersByProvider() {
        return null;
    }


}