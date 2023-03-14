package pidev.afarshop.Controller.Payment;



import pidev.afarshop.Entity.*;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import org.springframework.http.HttpStatus;
import pidev.afarshop.Service.Payement.StripePayment;

import java.beans.JavaBean;
import java.lang.String;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;



@RestController
@RequestMapping("/api/payement/stripe")

public class StripeController {

    StripePayment stripePayment;

    public StripeController() {
        this.stripePayment = new StripePayment();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createPaymentYessin(@RequestParam("amount") int amount, @RequestParam("currency") String currency) {
        Stripe.apiKey = "sk_test_51Ml2EfBaez0hOnxgmENtVRP5X5qbUvzlvZAtvkdTw3uuAsS98zofxtNDoLdlzXGulHg3kJDKVtoRidt7k6uoU5s500Rlwr8sqE";

        try {
            Charge charge = Charge.create(Map.of(
                    "amount", amount,
                    "currency", currency,
                    "source", "tok_visa"
            ));


            return ResponseEntity.ok(charge.toString());
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
