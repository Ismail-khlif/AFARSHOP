package pidev.afarshop.Service.Order1;

import pidev.afarshop.Entity.*;
import pidev.afarshop.Service.*;
import pidev.afarshop.Repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class Order1Service implements ICRUDService<Order1,Long> , IOrderServices {

    Order1Repository order1Repository;
    @Override
    public List<Order1> findAll() {
        return order1Repository.findAll();
    }

    @Override
    public Order1 retrieveItem(Long idItem) {
        return order1Repository.findById(idItem).get();
    }

    @Override
    public Order1 add(Order1 order1) {
        return order1Repository.save(order1);
    }

    @Override
    public void delete(Long orderId) {
        order1Repository.deleteById(orderId);

    }

    @Override
    public Order1 update(Order1 order1) {
        return order1Repository.save(order1);
    }

    @Override
    public Map<String, List<Order1>> displayOrdersByProvider() {
        return null;
    }


}