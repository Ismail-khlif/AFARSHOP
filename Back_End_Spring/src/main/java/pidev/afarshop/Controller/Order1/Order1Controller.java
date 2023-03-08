package pidev.afarshop.Controller.Order1;


import pidev.afarshop.Entity.*;
import pidev.afarshop.Service.Order1.Order1Service;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/api/Order1/")
public class Order1Controller {

    Order1Service Order1Service;

    @GetMapping("/retrive_all_Order1s")
    public List<Order1> retrieveOrder1List(){
        return Order1Service.findAll();
    }

    @GetMapping("/retrive_Order1/{Order1Id}")
    public Order1 retrieveOrder1(@PathVariable("Order1Id") Long Order1Id){
        return Order1Service.retrieveItem(Order1Id);
    }

    @PostMapping("/add_Order1")
    public Order1 addOrder1(@RequestBody Order1 Order1){
        return Order1Service.add(Order1);
    }

    @PutMapping("/update_Order1")
    public Order1 updateOrder1(@RequestBody Order1 Order1){
        return Order1Service.update(Order1);
    }

    @DeleteMapping("/delete_Order1/{Order1Id}")
    public void deleteOrder1(@PathVariable("Order1Id") Long Order1Id){
        Order1Service.delete(Order1Id);
    }

}