

package pidev.afarshop.Service.Payement;



import com.twilio.Twilio;
import com.twilio.exception.TwilioException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Scheduled;

import pidev.afarshop.Entity.*;
import pidev.afarshop.Repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import pidev.afarshop.Service.Payement.IPaymentServices;


import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import org.springframework.core.env.Environment;

@Service
@Slf4j
@AllArgsConstructor
public class PaymentServices implements IPaymentServices {
    @Autowired
    BillRepository billRepository;

    private final String accountSid = "AC20887c66c515d0f7f341cb39c5c99ff7";
    private final String authToken = "a269039bc1779b0b2dc59260efb2c1b4";

    private final SmsService smsService;

    @Autowired
    public PaymentServices(SmsService smsService) {
        this.smsService = smsService;
    }

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    private Environment environment;

    public void init() {
        String accountSid = environment.getProperty("accountSid");
        String authToken = environment.getProperty("authToken");

        try {
            Twilio.init(accountSid, authToken);
        } catch (TwilioException ex) {
            // log error
        }
    }


    @Override
    public List<Payment> retrieveAllPayments() {
         return paymentRepository.findAll();
    }

    @Transactional
    public String chooseMethod(Payment p, long idBill){

        if (p.getPaymentMethod()==PaymentMethod.Cash)
        {
            addPayment(p,idBill);
        }
        else if (p.getPaymentMethod()==PaymentMethod.Card){

            Bill bill = billRepository.findById(idBill).orElse(null);
            if  (bill.getInstallmentsNb()==1)
            {
           return "Votre paiement payment global est "+ bill.getPaymentAmount();
            }
            if  (bill.getInstallmentsNb()==4)
            {
               return "Votre paiement payment global est "+ bill.getPaymentAmount()*(110/100) +"sur 4 tranches";
            }
            if  (bill.getInstallmentsNb()==12)
            {
                return "Votre paiement payment global est "+ bill.getPaymentAmount()*(120/100) +"sur 12 tranches";
            }


            
        }
        return null;
    }

    @Override
    public Payment addPayment(Payment payment, Long idBill) {

        Bill bill = billRepository.findById(idBill).orElse(null);
        if  (bill.getInstallmentsNb()==1)
        {
            payment.setBillPayment(bill);
            payment.setInstallmentAmount(bill.getPaymentAmount());
            payment.setPaid(true);
            payment.setPaymentDate(Calendar.getInstance().getTime());
            payment.setDueDate(Calendar.getInstance().getTime());
            paymentRepository.save(payment);

        }

        if (bill.getInstallmentsNb()==4 || bill.getInstallmentsNb()==12)

        {
            Payment p = new Payment();
            p.setBillPayment(bill);
            p.setInstallmentAmount(bill.getPaymentAmount()/bill.getInstallmentsNb());
            p.setPaid(true);
            p.setPaymentDate(Calendar.getInstance().getTime());
            p.setPaymentMethod(payment.getPaymentMethod());
            p.setDueDate(Calendar.getInstance().getTime());
            paymentRepository.save(p);


            for (int i=1;i<bill.getInstallmentsNb();i++)
            {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.MONTH, (12 / bill.getInstallmentsNb() * i));
                Payment p1 = new Payment();
                p1.setBillPayment(bill);
                p1.setInstallmentAmount(bill.getPaymentAmount()/bill.getInstallmentsNb());
                p1.setPaymentMethod(payment.getPaymentMethod());
                p1.setDueDate(calendar.getTime());
                paymentRepository.save(p1);
            }
        }
        return payment;
    }



    @Override

    public Payment updatePayment(Long idPayment) {
        Payment p=paymentRepository.findById(idPayment).orElse(null);
        p.setPaid(true);
        p.setPaymentDate(Calendar.getInstance().getTime());
        paymentRepository.save(p);
        return p;
    }

    @Override
    public List<Payment> retrievePaymentsByBill(Long idBill) {
        return paymentRepository.findPaymentByBillId(idBill);

    }

    @Override
    @Scheduled(cron = "0 0 12 * * *") // Exécution tous les jours à midi
    public void alertPayment() {

        Date today = new Date();

        // Recherche des paiements qui dépassent la date limite
        List<Payment> overduePayments = paymentRepository.findByDueDateBeforeAndPaidIsFalse(today);
        String fromNumber = environment.getProperty("+15073846037");

        // Envoi d'un SMS pour chaque paiement en retard

        for (Payment payment : overduePayments) {

            String message = "Votre paiement de " + payment.getInstallmentAmount() + "dt est en retard. Veuillez effectuer le paiement prévu le "+payment.getDueDate()+" dès que possible.";
            try {
                smsService.sendSms(payment.getBillPayment().getOrder1().getUser().getTelNum(), message);
            } catch (TwilioException e) {
                // Gérer les erreurs d'envoi de SMS
            }
            System.out.println(message);
            System.out.println(payment.getDueDate());
            //String message = "Votre paiement de " + payment.getInstallmentAmount() + "€ est en retard. Veuillez effectuer le paiement dès que possible.";
            //Message.creator(new PhoneNumber(payment.getBillPayment().getOrder1().getUser().getTelNum()), new PhoneNumber(fromNumber), message).create();
        }
    }

}

