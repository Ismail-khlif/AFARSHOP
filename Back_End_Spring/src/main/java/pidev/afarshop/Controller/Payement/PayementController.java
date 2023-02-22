package pidev.afarshop.Controller.Payement;


import pidev.afarshop.Entity.*;
import pidev.afarshop.Repository.*;
import pidev.afarshop.Service.Payement.PayementServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/api/payement/")
public class PayementController {

    PayementServices payementService;
    @GetMapping("/retrive_all_payements")
    public List<Payement> retrievePayementList(){
        return payementService.findAll();
    }

    @GetMapping("/retrive_payement/{payementId}")
    public Payement retrievePayement(@PathVariable("payementId") Long payementId){
        return payementService.retrieveItem(payementId);
    }

    @PostMapping("/add_payement")
    public Payement addPayement(@RequestBody Payement payement){
        return payementService.add(payement);
    }

    @PutMapping("/update_payement")
    public Payement updatePayement(@RequestBody Payement payement){
        return payementService.update(payement);
    }

    @DeleteMapping("/delete_payement/{payementId}")
    public void deletePayement(@PathVariable("payementId") Long payementId){
        payementService.delete(payementId);
    }
}