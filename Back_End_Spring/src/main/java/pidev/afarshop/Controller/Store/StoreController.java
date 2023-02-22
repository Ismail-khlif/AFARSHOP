package pidev.afarshop.Controller.Store;

import pidev.afarshop.Entity.*;
import pidev.afarshop.Service.Store.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/store")
public class StoreController {

    StoreService storeService;


    @PostMapping("/addStore")
    public Store add(@RequestBody Store store)  {

        return storeService.add(store);
    }
    @GetMapping("/get_all_Stores")
    public List<Store> findAll() {
        return storeService.findAll();
    }
    @PutMapping("/updateStore")
    public Store update (@RequestBody Store store)  {
        return storeService.update(store);
    }

    @DeleteMapping("/deleteStore/{storeId}")
    public void delete(@PathVariable("storeId") Long storeId){
        storeService.delete(storeId);
    }
    @GetMapping("/findStoreByName/{storeName}")
    public Store findStoreByName(@PathVariable("storeName") String storeName){
        return storeService.findStoreByName(storeName);
    }

    @GetMapping("/getStore/{storeId}")
    public Store findStore(@PathVariable("storeId") Long storeId) {
        return storeService.retrieveItem(storeId);
    }
}