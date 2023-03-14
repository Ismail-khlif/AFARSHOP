package pidev.afarshop.Service.Payement;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.String;

import pidev.afarshop.Entity.*;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import pidev.afarshop.Entity.Payment;

@Service
@Slf4j

@Configuration
public class StripePayment {

/*
    @Value("${stripe.key.secret}")
    String secretKey;

   /* private StripePayment stripePayment;
    public StripePayment() {
        this.stripePayment = new StripePayment();
    }
    */
/*
    public PaymentIntent paymentIntent(Payment paymentIntent) throws StripeException {
        Stripe.apiKey = "sk_test_51MfpbjFZqNx8XmmhC1sHRKoes1AgZ98bQTRXs27ztbjZ6X4mBaV5X2oAif5LaLmSkQSqZEi7bUyFe4ukscn5Jjy200DlYLAV3X";
        //Stripe.apiKey = secretKey;
        List<String> paymentMethodTypes = new ArrayList();
        paymentMethodTypes.add(paymentIntent.getPaymentMethodToken());
        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentIntent.getAmount());
        params.put("currency", paymentIntent.getCurrency());

        params.put("payment_method_types", paymentMethodTypes);

        return PaymentIntent.create(params);



    }






    public Charge chargeCreditCard(String token, int amount) throws StripeException {
        //Stripe.apiKey = secretKey;
        Stripe.apiKey = "sk_test_51MfpbjFZqNx8XmmhC1sHRKoes1AgZ98bQTRXs27ztbjZ6X4mBaV5X2oAif5LaLmSkQSqZEi7bUyFe4ukscn5Jjy200DlYLAV3X";
        System.out.println(secretKey);

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", amount*100);
        chargeParams.put("currency", "usd");
        chargeParams.put("source", token);


        return Charge.create(chargeParams);
    }
    */


    public Charge createChargeYessin(Long amount, String currency, String source) throws StripeException {
        //Stripe.apiKey = STRIPE_SECRET_KEY;
        Stripe.apiKey = "sk_test_51Ml2EfBaez0hOnxgmENtVRP5X5qbUvzlvZAtvkdTw3uuAsS98zofxtNDoLdlzXGulHg3kJDKVtoRidt7k6uoU5s500Rlwr8sqE";
        // Set the charge creation parameters

        ChargeCreateParams params = ChargeCreateParams.builder()
                .setAmount(amount)
                .setCurrency(currency)
                .setSource(source)
                .build();

        // Create the charge
        return Charge.create(params);
    }
/*
    public PaymentIntent confirm(String id) throws StripeException {
        Stripe.apiKey = secretKey;
        PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
        Map<String, Object> params = new HashMap<>();
        params.put("payment_method", "pm_card_visa");
        paymentIntent.confirm(params);
        return paymentIntent;
    }
    public PaymentIntent cancel(String id) throws StripeException {
        Stripe.apiKey = secretKey;
        PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
        paymentIntent.cancel();
        return paymentIntent;
    }
 */

}
