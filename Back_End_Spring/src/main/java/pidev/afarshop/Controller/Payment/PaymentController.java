package pidev.afarshop.Controller.Payment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.afarshop.Entity.Payment;
import pidev.afarshop.Service.Payement.PaymentServices;

import java.util.List;

@RestController
@RequestMapping("/api/payment")

public class PaymentController {

    @Autowired
    PaymentServices paymentService;

    @GetMapping("/retrieve-all-payments")
    @ResponseBody
    public List<Payment> getPayments() {
        List<Payment> listPayments = paymentService.retrieveAllPayments();
        return listPayments;
    }

    @PostMapping("/add-payment/{bill-id}")
    @ResponseBody
    public void addPayment(@RequestBody Payment p, @PathVariable("bill-id") Long billId)
    {
        paymentService.chooseMethod(p, billId);
    }

    @PutMapping("/modify-payment/{idPayment}")
    @ResponseBody
	    public Payment updatePayment(@PathVariable("idPayment") Long idPayment) {
	        return paymentService.updatePayment(idPayment);
	    }

    @GetMapping("/retrieve-payment-by-bill/{bill-id}")
    @ResponseBody
    public List<Payment> getPaymentsByBill(@PathVariable("bill-id") Long billId) {
        List<Payment> listPayments = paymentService.retrievePaymentsByBill(billId);
        return listPayments;
    }

}

