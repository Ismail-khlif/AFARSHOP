package pidev.afarshop.Dto;

import lombok.Builder;
import lombok.Data;
import pidev.afarshop.Entity.Delivery;
import pidev.afarshop.Entity.Payment;
import pidev.afarshop.Entity.PaymentMethod;
import java.util.Date;

@Data
@Builder
public class PaymentDto {

    private Long paymentId;

    private boolean paid;

    private Date paymentDate;

    private double installmentAmount;

    private PaymentMethod paymentMethod;

    private Date dueDate;

    private BillDto billPayment;
    public Payment toEntity(PaymentDto paymentDto) {
        if (paymentDto == null) {
            //TODO EXCEPTION ERRROR
            return null;
        }
        return Payment.builder()
                .paymentId(paymentDto.getPaymentId())
                .paid(paymentDto.isPaid())
                .paymentDate(paymentDto.getPaymentDate())
                .installmentAmount(paymentDto.getInstallmentAmount())
                .paymentMethod(paymentDto.getPaymentMethod())
                .dueDate(paymentDto.getDueDate())
                .billPayment(paymentDto.getBillPayment())
                .build();
    }
    public PaymentDto toDto(Payment payment){
        if(payment==null){
            //TODO EXCEPTION ERRROR
            return null;
        }
        return  PaymentDto.builder()
                .paymentId(payment.getPaymentId())
                .paid(payment.isPaid())
                .paymentDate(payment.getPaymentDate())
                .installmentAmount(payment.getInstallmentAmount())
                .paymentMethod(payment.getPaymentMethod())
                .dueDate(payment.getDueDate())
                .billPayment(payment.getBillPayment())
                .build();
    }

}
