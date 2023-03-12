package pidev.afarshop.Service.Cart;


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
public class CartServices implements ICRUDService<Cart,Long> , ICartServices {

    CartRepository cartRepsitory;

    @Override
    public List<Cart> findAll() {

        return cartRepsitory.findAll();
    }

    @Override
    public Cart retrieveItem(Long idCart) {
        return cartRepsitory.findById(idCart).get();
    }

    @Override
    public Cart add(Cart cart) {

        return cartRepsitory.save(cart);
    }

    @Override
    public void delete(Long cartId) {

        cartRepsitory.deleteById(cartId);
    }

    @Override
    public Cart update(Cart cart) {
        return cartRepsitory.save(cart);
    }
    //here
    public Map<Product, Integer> items = new HashMap<>();

    public Map<Product, Integer> addItem(Product product, int quantity) {
        if (items.containsKey(product)) {
            int currentQuantity = items.get(product);
            items.put(product, currentQuantity + quantity);
        } else {
            items.put(product, quantity);
        }
        return items;
    }


}
