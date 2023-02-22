package pidev.afarshop.Controller.Delivery;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pidev.afarshop.Entity.*;
import pidev.afarshop.Service.Delivery.DeliveryService;

import java.util.List;

@RestController
@RequestMapping("/api/delivery")

public class DeliveryController {

    @Autowired
    DeliveryService deliveryService;

    @GetMapping("/retrieve-all-deliveries")
    @ResponseBody
    public List<Delivery> getDeliveries() {
        List<Delivery> listDeliveries = deliveryService.retrieveAllDeliveries();
        return listDeliveries;
    }

    @PostMapping("/add-delivery")
    @ResponseBody
    public Delivery addDelivery(@RequestBody Delivery d)
    {
        Delivery delivery = deliveryService.addDelivery(d);
        return delivery;
    }

    @PutMapping("/modify-delivery")
    @ResponseBody
    public Delivery modifyDelivery(@RequestBody Delivery delivery) {
        return deliveryService.updateDelivery(delivery);
    }

    @DeleteMapping("/remove-delivery/{delivery-id}")
    @ResponseBody
    public void removeDelivery(@PathVariable("delivery-id") Long deliveryId) {
        deliveryService.deleteDelivery(deliveryId);
    }
}