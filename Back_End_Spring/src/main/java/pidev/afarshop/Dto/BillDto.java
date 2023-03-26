package pidev.afarshop.Dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import pidev.afarshop.Entity.Bill;

import java.util.Date;
import java.util.Set;

@Builder
@Data
public class BillDto {

    private Long billId;

    private int installmentsNb ;

    private double paymentAmount;

    private Date billDate;
    @JsonIgnore
    private Order1Dto order1;

    private Set<PaymentDto> payments;

    public BillDto toDto(Bill bill){
        if(bill==null){
            //TODO ERROR EXCEPTION
            return null;
        }
        return BillDto.builder()
                .billId(bill.getBillId())
                .installmentsNb(bill.getInstallmentsNb())
                .paymentAmount(bill.getPaymentAmount())
                .billDate(bill.getBillDate())
                .order1(bill.getOrder1())
                .payments(bill.getPayments())
                .build();
    }
    public Bill toEntity(BillDto billDto){
        if(billDto ==null){
            //TODO ERROR EXCEPTION
            return null;
        }
        return Bill.builder()
                .billId(billDto.getBillId())
                .installmentsNb(billDto.getInstallmentsNb())
                .paymentAmount(billDto.getPaymentAmount())
                .billDate(billDto.getBillDate())
                .order1(billDto.getOrder1())
                .payments(billDto.getPayments())
                .build();
    }
}
