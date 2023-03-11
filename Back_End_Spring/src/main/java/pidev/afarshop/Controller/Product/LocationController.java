package pidev.afarshop.Controller.Product;

import pidev.afarshop.Entity.ClientLocationRequest;

import pidev.afarshop.Repository.DeliveryRepository;
import pidev.afarshop.Repository.clientLocationRequestRepository;
import pidev.afarshop.Service.Delivery.DeliveryService;
import pidev.afarshop.Service.Product.LocationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
@AllArgsConstructor
public class LocationController {
    LocationService locationService;

    //@Scheduled(fixedRate = 5000) // Run every 30s
    @GetMapping("/map")
    public String getGeolocation() throws JsonProcessingException {
        return locationService.getGeolocation();
    }
}