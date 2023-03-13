package pidev.afarshop.Controller.Order1;


import pidev.afarshop.Entity.*;
import pidev.afarshop.Service.Order1.Order1Service;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/api/order/")
public class OrderController {

    Order1Service orderService;

    @GetMapping("/retrive_all_orders")
    public List<Order1> retrieveOrderList(){
        return orderService.findAll();
    }

    @GetMapping("/retrive_order/{orderId}")
    public Order1 retrieveOrder(@PathVariable("orderId") Long orderId){
        return orderService.retrieveItem(orderId);
    }

    @PostMapping("/add_order")
    public Order1 addOrder(@RequestBody Order1 order1){
        return orderService.add(order1);
    }

    @PutMapping("/update_order")
    public Order1 updateOrder(@RequestBody Order1 order1){
        return orderService.update(order1);
    }

    @DeleteMapping("/delete_order/{orderId}")
    public void deleteOrder(@PathVariable("orderId") Long orderId){
        orderService.delete(orderId);
    }

}