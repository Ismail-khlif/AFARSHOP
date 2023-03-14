package pidev.afarshop.Controller.Bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.afarshop.Entity.Bill;
import pidev.afarshop.Entity.Order1;
import pidev.afarshop.Service.Bill.BillService;

import java.util.List;
@RestController

@RequestMapping("/api/bill")
public class BillController {
    @Autowired
    BillService billService;

    /*@GetMapping("/retrieve-all-bills")
    @ResponseBody
    public List<Bill> getBills() {
        List<Bill> listBills = billService.retrieveAllBills();
        return listBills;
    } */

    @GetMapping("/retrive-Bill/{BillId}")
    public Bill retrieveBill(@PathVariable("BillId") Long billId){
        return billService.retrieveBillById(billId);
    }

    @PostMapping("/add-bill/{order-id}")
    @ResponseBody
    public Bill addBill(@RequestBody Bill d, @PathVariable("order-id") Long orderId)
    {
        Bill bill = billService.addBill(d, orderId);
        return bill;
    }



    @GetMapping("/retrieveBillByUser")
    @ResponseBody
    public List<Bill> retrieveBillByUser() {
        List<Bill> listbills = billService.retrieveBillByUser();
        return listbills;
    }
}
