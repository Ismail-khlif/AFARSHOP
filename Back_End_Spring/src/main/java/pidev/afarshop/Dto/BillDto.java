package pidev.afarshop.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pidev.afarshop.Entity.Bill;
import pidev.afarshop.Entity.Order1;
import pidev.afarshop.Entity.Payment;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDto {

    private Long billId;

    private int installmentsNb ;

    private double paymentAmount;

    private Date billDate;
    @JsonIgnore

    private Order1Dto order1;
    @JsonIgnore
    private Set<PaymentDto> payments;

    public static BillDto toDto(Bill bill){
        if(bill==null){
            //TODO ERROR EXCEPTION
            return null;
        }
        Order1Dto order1Dto = Order1Dto.toDto(bill.getOrder1());
        Set<PaymentDto> paymentDtos = bill.getPayments().stream()
                .map(PaymentDto::toDto)
                .collect(Collectors.toSet());
        return BillDto.builder()
                .billId(bill.getBillId())
                .installmentsNb(bill.getInstallmentsNb())
                .paymentAmount(bill.getPaymentAmount())
                .billDate(bill.getBillDate())
                .order1(order1Dto)
                .payments(paymentDtos)
                .build();
    }

    public static Bill toEntity(BillDto billDto){
        if(billDto ==null){
            //TODO ERROR EXCEPTION
            return null;
        }
        Set<Payment> payments = billDto.getPayments().stream()
                .map(PaymentDto::toEntity)
                .collect(Collectors.toSet());
        Order1 order1 = Order1Dto.toEntity(billDto.getOrder1());
        return Bill.builder()
                .billId(billDto.getBillId())
                .installmentsNb(billDto.getInstallmentsNb())
                .paymentAmount(billDto.getPaymentAmount())
                .billDate(billDto.getBillDate())
                .order1(order1)
                .payments(payments)
                .build();
    }
}