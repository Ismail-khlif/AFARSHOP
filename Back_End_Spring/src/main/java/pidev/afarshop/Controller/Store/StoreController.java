package pidev.afarshop.Controller.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.multipart.MultipartFile;
import pidev.afarshop.Entity.*;
import pidev.afarshop.Repository.FileSystemRepository;
import pidev.afarshop.Service.Store.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/store")
public class StoreController {
    @Autowired
    StoreService storeService;

    @Autowired
    FileSystemRepository fileSystemRepository;


    @RestControllerAdvice
    public class MyExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<List<String>> handleValidationException(MethodArgumentNotValidException ex) {
            List<String> errors = ex.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(errors);
        }
    }


    @PostMapping(value = {"/addStore"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Store add (@RequestParam("image") MultipartFile image, @Valid @RequestParam String storeName, @RequestParam String storeLocation, @RequestParam String storeDescription, @RequestParam int contactInformation, @RequestParam String storeEmailAddress ) throws Exception {
        String path = fileSystemRepository.save(image);
        Store store = new Store();
        store.setStoreName(storeName);
        store.setStoreEmailAddress(storeEmailAddress);
        store.setStoreDescription(storeDescription);
        store.setImage(image.getBytes());
        store.setContactInformation(contactInformation);
        store.setStoreLocation(storeLocation);
        store.setImagePath(path);
        return storeService.addStore(store);

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
    public void deleteStore(@PathVariable("storeId") Long storeId){
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


    @GetMapping("/Tpo5LikedStores")
    public List<Store> top5LikedStores(){
        List<Store> stores =storeService.top5LikedStores();
        return stores;
    }
}