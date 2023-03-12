package pidev.afarshop.Service.Order1;

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
public class Order1Service implements ICRUDService<Order1,Long> , IOrderServices {


    Order1Repository orderRepository;
    CartRepository cartRepository;
    DiscountCodeRepository discountCodeRepository;
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
    public Map<String,List<Order1>> displayOrdersByProvider() {
        List<String> findProviderNamesWithOrder = orderRepository.findProviderNamesWithOrder();
        Map<String, List<Order1>> map = new HashMap<>();
        for (String obj : findProviderNamesWithOrder) {
            map.put(obj,orderRepository.findByDelivery_Provider_ProviderName(obj));
        }
        return map;
    }
    //here
    public Order1 AssignCartToOrder(Long orderId, Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        Order1 order = orderRepository.findById(orderId).orElse(null);
        order.setCart(cart);

        return orderRepository.save(order);
    }


    public Order1 OrderAfterDiscount(Long orderId, Long CodePromo){

        Order1 order = orderRepository.findById(orderId).get();

        DiscountCode discountCode = discountCodeRepository.findById(CodePromo).get();
        System.out.println(discountCode);
        if(discountCode != null){
            if(discountCode.getUsed()==false){
                float discount = discountCode.getDiscount();
                float discountedBill = order.getAmountBill()*(1- discount/100) ;
                order.setAmountBill(discountedBill);
                orderRepository.save(order);
                discountCode.setUsed(true);
                discountCodeRepository.save(discountCode);
            }else {
                System.out.println("CodePromo "+discountCode.getCode()+" deja utilisé ! ");
            }
        }else {
            System.out.println("CodePromo n'existe pas");
        }
        return order;
    }


    /*@Override
    public Map<String, List<Order1>> displayOrdersByProvider() {
        return null;
    }*/


}

