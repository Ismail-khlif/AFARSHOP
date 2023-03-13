package pidev.afarshop.Controller.Delivery;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pidev.afarshop.Entity.*;
import pidev.afarshop.Service.Delivery.DeliveryService;

import java.util.Date;
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

    @PostMapping("/add-delivery/{order-id}")
    @ResponseBody
    public Delivery addDelivery(@RequestBody Delivery d, @PathVariable("order-id") Long orderId)
    {
        Delivery delivery = deliveryService.addDelivery(d,orderId);
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

    @GetMapping("/retrieveDeliveryByUser")
    @ResponseBody
    public List<Delivery> retrieveDeliveryByUser() {
        List<Delivery> listDeliveries = deliveryService.retrieveDeliveryByUser();
        return listDeliveries;
    }

    @GetMapping("/bestDeliveryMode/{startDate}/{endDate}")
    @ResponseBody
    public String bestDeliveryMode(@PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date from, @PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date to)
    {
        return deliveryService.bestDeliveryMode(from,to);

    }




}