package pidev.afarshop.Service.Order;

import pidev.afarshop.Entity.*;
import pidev.afarshop.Service.*;
import pidev.afarshop.Repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class OrderService implements ICRUDService<Order,Long> , IOrderServices {

    OrderRepository orderRepository;
    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order retrieveItem(Long idItem) {
        return orderRepository.findById(idItem).get();
    }

    @Override
    public Order add(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(Long orderId) {
        orderRepository.deleteById(orderId);

    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }
    public Map<String,List<Order>> displayOrdersByProvider() {
        List<String> findProviderNamesWithOrder = orderRepository.findProviderNamesWithOrder();
        Map<String, List<Order>> map = new HashMap<>();
        for (String obj : findProviderNamesWithOrder) {
            map.put(obj,orderRepository.findByDelivery_Provider_ProviderName(obj));

        }
        return map;
    }
}