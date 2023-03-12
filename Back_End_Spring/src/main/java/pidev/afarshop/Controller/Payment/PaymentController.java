package pidev.afarshop.Controller.Payment;


import pidev.afarshop.Entity.*;
import pidev.afarshop.Service.Payement.PaymentServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/api/payement/")
public class PaymentController {

    PaymentServices payementService;
    @GetMapping("/retrive_all_payements")
    public List<Payment> retrievePayementList(){
        return payementService.findAll();
    }

    @GetMapping("/retrive_payement/{payementId}")
    public Payment retrievePayement(@PathVariable("payementId") Long payementId){
        return payementService.retrieveItem(payementId);
    }

    @PostMapping("/add_payement")
    public Payment addPayement(@RequestBody Payment payment){
        return payementService.add(payment);
    }

    @PutMapping("/update_payement")
    public Payment updatePayement(@RequestBody Payment payment){
        return payementService.update(payment);
    }

    @DeleteMapping("/delete_payement/{payementId}")
    public void deletePayement(@PathVariable("payementId") Long payementId){
        payementService.delete(payementId);
    }
}