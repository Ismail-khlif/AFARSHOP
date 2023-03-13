package pidev.afarshop.Controller.Order1;


import pidev.afarshop.Entity.*;
import pidev.afarshop.Service.Order1.Order1Service;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/api/order/")
public class Order1Controller {

    Order1Service order1Service;

    @GetMapping("/retrive_all_orders")
    public List<Order1> retrieveOrderList(){
        return order1Service.findAll();
    }

    @GetMapping("/retrive_order/{orderId}")
    public Order1 retrieveOrder(@PathVariable("orderId") Long orderId){
        return order1Service.retrieveItem(orderId);
    }

    @PostMapping("/add_order")
    public Order1 addOrder(@RequestBody Order1 order1){
        return order1Service.add(order1);
    }

    @PutMapping("/update_order")
    public Order1 updateOrder(@RequestBody Order1 order1){
        return order1Service.update(order1);
    }

    @DeleteMapping("/delete_order/{orderId}")
    public void deleteOrder(@PathVariable("orderId") Long orderId){
        order1Service.delete(orderId);
    }

}