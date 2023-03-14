package pidev.afarshop.Controller.Store;

import com.lowagie.text.DocumentException;
import com.twilio.Twilio;
import com.twilio.exception.TwilioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.multipart.MultipartFile;
import pidev.afarshop.Entity.*;
import pidev.afarshop.Repository.FileSystemRepository;
import pidev.afarshop.Repository.StoreExcelExporter;
import pidev.afarshop.Repository.StoreRepository;
import pidev.afarshop.Service.Payement.SmsService;
import pidev.afarshop.Service.Store.StorePdfGenerator;
import pidev.afarshop.Service.Store.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    StorePdfGenerator storePdfGenerator;
    @Autowired
    StoreRepository storeRepository;


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
    public Store add (@RequestParam("image") MultipartFile image, @Valid @RequestParam String storeName,
                      @RequestParam String storeLocation, @RequestParam String storeDescription,
                      @RequestParam int contactInformation, @RequestParam String storeEmailAddress ) throws Exception {
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

       /* Twilio.init("YOUR_ACCOUNT_SID", "YOUR_AUTH_TOKEN");
        Message message = Message.creator(
                        new PhoneNumber(phone),
                        new PhoneNumber("YOUR_TWILIO_PHONE_NUMBER"), // Replace with your own Twilio phone number
                        "New store created: " + name + ", " + location)
                .create();
        System.out.println(message.getSid()); // Optional - print the message SID for debugging purposes

        return "success";*/

    }

    @PutMapping("/affectScore/{storeId}")
    public Store affectScore (@PathVariable ("storeId") Long storeId){
        return storeService.affectScore(storeId);
    }

    @PutMapping("/affectStoreToCategory")
    public void affectStoreToCategory(Long storeId, Long categoryId){
        storeService.affectStoreToCategory(storeId,categoryId);
    }

    @PutMapping("/affectEvaluation/{storeId}")
    public Store affectEvaluation(@PathVariable("storeId") Long storeId){
        return storeService.affectEvaluation(storeId);
    }

    @GetMapping("/Srore")
    public Store findHighestScoredStore(){
        return storeService.findHighestScoredStore();
    }

    @GetMapping("/Tpo5LikedStores")
    public List<Store> top5LikedStores(){
        List<Store> stores =storeService.top5LikedStores();
        return stores;
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



    @GetMapping("/stores/export")
    public void exportToExcel (HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=stores.xlsx";
        response.setHeader(headerKey, headerValue);

        List<Store> storeList = storeService.findAll();

        StoreExcelExporter storeExcelExporter = new StoreExcelExporter(storeList);
        storeExcelExporter.export(response);
    }

    @GetMapping("/pdf/generateStoreEvaluation/{storeId}")
    public void generatePDFStore(HttpServletResponse response, @PathVariable("storeId") Long storeId) throws IOException, DocumentException
    { response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attatchement; filename=Store.." + currentDateTime + ".pdf";

        response.setHeader(headerKey, headerValue);


        StorePdfGenerator exporter = new StorePdfGenerator();
        Store store= storeRepository.findById(storeId).orElse(null);
        exporter.generatePdfReport(response, store);
    }
}

