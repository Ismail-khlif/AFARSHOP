package pidev.afarshop.Controller.StoreLocation;

import pidev.afarshop.Dto.StoreLocationsDto;
import pidev.afarshop.Entity.*;
import pidev.afarshop.Repository.*;
import pidev.afarshop.Service.StoreLocation.StoreLocationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/api/storeLocation/")
public class StoreLocationController {

    StoreLocationService storeLocationService;
    @GetMapping("/retrive_all_StoreLocations")
    public List<StoreLocations> retrieveCartList(){
        return storeLocationService.findAll();
    }

    @GetMapping("/retrive_StoreLocations/{cartId}")
    public StoreLocationsDto retrieveCart(@PathVariable("cartId") Long cartId){
        return storeLocationService.retrieveItema(cartId);
    }

    @PostMapping("/add_StoreLocations")
    public StoreLocations addCart(@RequestBody StoreLocations cart){
        return storeLocationService.add(cart);
    }
    /*kk*/
    @PutMapping("/update_StoreLocations")
    public StoreLocations updateCart(@RequestBody StoreLocations cart){
        return storeLocationService.update(cart);
    }

    @DeleteMapping("/delete_StoreLocations/{cartId}")
    public void deleteCart(@PathVariable("cartId") Long cartId){
        storeLocationService.delete(cartId);
    }

}
